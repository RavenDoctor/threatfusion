export interface ThreatLookupResponse {
    ipAddress: string;
    threatScore: number;
    country: string;
    riskLevel: string;
}