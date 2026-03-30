// src/components/ThemeSelector.js
import React from "react";

export default function ThemeSelector({ themen, selectedThemaId, onSelect }) {
    return (
        <div className="card">
            <h2>Thema wählen</h2>
            <p>Wähle ein Thema aus, mit dem du das Quiz starten möchtest.</p>

            <select
                value={selectedThemaId || ""}
                onChange={(e) => onSelect(Number(e.target.value))}
            >
                <option value="">Bitte Thema auswählen...</option>
                {themen.map((t) => (
                    <option key={t.themaId} value={t.themaId}>
                        {t.titel}
                    </option>
                ))}
            </select>
        </div>
    );
}