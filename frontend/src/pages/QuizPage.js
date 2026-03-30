import React, { useEffect, useMemo, useState } from "react";
import { getFragen } from "../api/api";

function normalizeFrage(f) {
    const frageId = f.frageId ?? f.questionId ?? f.id;
    const text = f.frageText ?? f.questionText ?? f.text ?? "";

    const antwortenRaw = f.antworten ?? f.answers ?? [];

    const antworten = antwortenRaw.map((a) => ({
        id: a.antwortId ?? a.answerId ?? a.id,
        text: a.antwortText ?? a.answerText ?? a.text ?? "",
        correct:
            a.istKorrekt ??
            a.isCorrect ??
            a.is_correct ??
            a.correct ??
            false,
    }));

    return {
        id: frageId,
        text,
        answers: antworten,
    };
}

export default function QuizPage({ themaId, onFinish }) {
    const [fragen, setFragen] = useState([]);
    const [idx, setIdx] = useState(0);
    const [selectedAnswerId, setSelectedAnswerId] = useState(null);
    const [richtig, setRichtig] = useState(0);

    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const aktuelleFrage = useMemo(() => fragen[idx], [fragen, idx]);

    useEffect(() => {
        if (!themaId) return;

        async function loadFragen() {
            try {
                setLoading(true);
                setError(null);

                setIdx(0);
                setRichtig(0);
                setSelectedAnswerId(null);

                const data = await getFragen(themaId);
                const normalized = (data || []).map(normalizeFrage);

                setFragen(normalized);
            } catch (e) {
                console.error(e);
                setError("Fragen konnten nicht geladen werden.");
            } finally {
                setLoading(false);
            }
        }

        loadFragen();
    }, [themaId]);

    function next() {
        if (!aktuelleFrage) return;

        const selected = aktuelleFrage.answers.find(
            (a) => a.id === selectedAnswerId
        );

        const istRichtig = selected?.correct === true;
        const neuesErgebnis = istRichtig ? richtig + 1 : richtig;

        setRichtig(neuesErgebnis);
        setSelectedAnswerId(null);

        const isLast = idx === fragen.length - 1;

        if (isLast) {
            onFinish?.({
                themaId,
                correctAnswers: neuesErgebnis,
                totalQuestions: fragen.length,
            });
        } else {
            setIdx((prev) => prev + 1);
        }
    }

    if (!themaId) return null;

    return (
        <div className="card" style={{ marginTop: "1.5rem" }}>
            <h2>Quiz</h2>

            {loading && <p>Lade Fragen...</p>}
            {error && <p className="error">{error}</p>}

            {!loading && !error && fragen.length === 0 && (
                <p>Für dieses Thema sind noch keine Fragen vorhanden.</p>
            )}

            {!loading && !error && aktuelleFrage && (
                <>
                    <p className="info">
                        Frage <strong>{idx + 1}</strong> von <strong>{fragen.length}</strong>
                    </p>

                    <h3 style={{ marginTop: "1rem" }}>{aktuelleFrage.text}</h3>

                    <div style={{ marginTop: "1rem", display: "grid", gap: "0.5rem" }}>
                        {aktuelleFrage.answers.map((a) => (
                            <label
                                key={a.id}
                                style={{
                                    display: "flex",
                                    gap: "0.5rem",
                                    alignItems: "center",
                                }}
                            >
                                <input
                                    type="radio"
                                    name={`frage-${aktuelleFrage.id}`}
                                    checked={selectedAnswerId === a.id}
                                    onChange={() => setSelectedAnswerId(a.id)}
                                />
                                <span>{a.text}</span>
                            </label>
                        ))}
                    </div>

                    <button
                        style={{ marginTop: "1rem" }}
                        disabled={selectedAnswerId == null}
                        onClick={next}
                    >
                        {idx === fragen.length - 1
                            ? "Ergebnis anzeigen"
                            : "Nächste Frage"}
                    </button>
                </>
            )}
        </div>
    );
}