import { useState, useEffect } from "react";

interface ThreatLookupResponse {
    ipAddress: string;
    threatScore: number;
    country: string;
    riskLevel: string;
}

interface HistoryItem {
    id: number;
    ipAddress: string;
    threatScore: number;
    riskLevel: string;
    searchedAt: string;
}

function App() {

    const [ipAddress, setIpAddress] = useState("");
    const [result, setResult] =
        useState<ThreatLookupResponse | null>(null);
    const [history, setHistory] = useState<HistoryItem[]>([]);
    const totalSearches = history.length;

    const highRiskCount = history.filter(
        item => item.riskLevel === "HIGH"
    ).length;

    const mediumRiskCount = history.filter(
        item => item.riskLevel === "MEDIUM"
    ).length;

    const lowRiskCount = history.filter(
        item => item.riskLevel === "LOW"
    ).length;
    const loadHistory = async () => {

        const response = await fetch(
            "http://localhost:8080/api/history"
        );

        const data = await response.json();

        setHistory(data);
    };

    const searchIp = async () => {

        const response = await fetch(
            `http://localhost:8080/api/search/ip/${ipAddress}`
        );

        const data = await response.json();

        setResult(data);

        await loadHistory();
    };

    useEffect(() => {
        loadHistory();
    }, []);

    return (
        <div className="container">

            <div className="header">
                <h1>ThreatFusion</h1>
                <p>Threat Intelligence Platform</p>
            </div>

            <div className="search-box">

                <input
                    type="text"
                    value={ipAddress}
                    onChange={(e) => setIpAddress(e.target.value)}
                    placeholder="Enter IP Address"
                />

                <button onClick={searchIp}>
                    Analyze
                </button>

            </div>

            {result && (

                <div className="result-card">

                    <h2>Analysis Result</h2>

                    <div className="result-row">
                        <span>IP Address</span>
                        <span>{result.ipAddress}</span>
                    </div>

                    <div className="result-row">
                        <span>Country</span>
                        <span>{result.country}</span>
                    </div>

                    <div className="result-row">
                        <span>Threat Score</span>
                        <span>{result.threatScore}</span>
                    </div>

                    <div className="result-row">
                        <span>Risk Level</span>
                        <span className={`risk ${result.riskLevel.toLowerCase()}`}>
                            {result.riskLevel}
                        </span>
                    </div>

                </div>

            )}
            <div className="stats-grid">

                <div className="stat-card">
                    <h3>Total Searches</h3>
                    <p>{totalSearches}</p>
                </div>

                <div className="stat-card high-stat">
                    <h3>High Risk</h3>
                    <p>{highRiskCount}</p>
                </div>

                <div className="stat-card medium-stat">
                    <h3>Medium Risk</h3>
                    <p>{mediumRiskCount}</p>
                </div>

                <div className="stat-card low-stat">
                    <h3>Low Risk</h3>
                    <p>{lowRiskCount}</p>
                </div>

            </div>
            <div className="result-card" style={{ marginTop: "25px" }}>

                <h2>Search History</h2>

                <table className="history-table">

                    <thead>
                    <tr>
                        <th>IP Address</th>
                        <th>Threat Score</th>
                        <th>Risk Level</th>
                        <th>Searched At</th>
                    </tr>
                    </thead>

                    <tbody>

                    {history.map((item) => (

                        <tr key={item.id}>
                            <td>{item.ipAddress}</td>
                            <td>{item.threatScore}</td>
                            <td>
                                <span className={`risk ${item.riskLevel.toLowerCase()}`}>
                                    {item.riskLevel}
                                </span>
                            </td>
                            <td>
                                {new Date(item.searchedAt)
                                    .toLocaleString()}
                            </td>
                        </tr>

                    ))}
                    </tbody>

                </table>

            </div>
            <footer className="footer">

                <h3>About The Developer</h3>

                <p>
                    Built by Adam Shaddick.
                </p>

                <p>
                    Software Engineer & Cyber Security Graduate
                    specialising in Java, Spring Boot, React and
                    Security Engineering.
                </p>
                <div className="footer-links">

                    <a href="https://github.com/RavenDoctor/">
                        GitHub
                    </a>

                    <a href="https://www.linkedin.com/in/adshadk-455811323/">
                        LinkedIn
                    </a>

                </div>

            </footer>
        </div>
    );
}

export default App;