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
      <div>

        <h1>ThreatFusion</h1>

        <input
            type="text"
            value={ipAddress}
            onChange={(e) => setIpAddress(e.target.value)}
            placeholder="Enter IP Address"
        />

        <button onClick={searchIp}>
          Search
        </button>

        {result && (
            <div>
              <h2>Result</h2>

              <p>IP: {result.ipAddress}</p>
              <p>Country: {result.country}</p>
              <p>Threat Score: {result.threatScore}</p>
              <p>Risk Level: {result.riskLevel}</p>
            </div>
        )}

      </div>
  );
}

export default App;