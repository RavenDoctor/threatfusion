import { useState } from "react";

interface ThreatLookupResponse {
    ipAddress: string;
    threatScore: number;
    country: string;
    riskLevel: string;
}

function App() {

    const [ipAddress, setIpAddress] = useState("");
    const [result, setResult] =
        useState<ThreatLookupResponse | null>(null);

    const searchIp = async () => {

        const response = await fetch(
            `http://localhost:8080/api/search/ip/${ipAddress}`
        );

        const data = await response.json();

        setResult(data);
    };

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

        </div>
    );
}

export default App;