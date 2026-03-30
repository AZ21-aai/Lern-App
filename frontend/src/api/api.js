// src/api/api.js

const BASE_URL = "http://localhost:8086/api";

async function handleResponse(res) {
    if (!res.ok) {
        const text = await res.text();
        throw new Error(`HTTP ${res.status}: ${text}`);
    }

    const contentType = res.headers.get("content-type") || "";
    if (!contentType.includes("application/json")) return res.text();
    return res.json();
}

export async function getThemen() {
    const res = await fetch(`${BASE_URL}/themen`);
    return handleResponse(res);
}

export async function getFragen(themaId) {
    const res = await fetch(`${BASE_URL}/fragen?themaId=${themaId}`);
    return handleResponse(res);
}

export async function saveErgebnis(dto) {
    const res = await fetch(`${BASE_URL}/ergebnis`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dto),
    });
    return handleResponse(res);
}