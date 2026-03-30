import React, { useEffect, useState } from "react";
import "./App.css";
import { getThemen } from "./api/api";
import ThemeSelector from "./components/ThemeSelector";
import QuizPage from "./pages/QuizPage";

function App() {
    const [themen, setThemen] = useState([]);
    const [selectedThemaId, setSelectedThemaId] = useState(null);
    const [result, setResult] = useState(null);

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        async function loadThemen() {
            try {
                setLoading(true);
                setError(null);

                const data = await getThemen();
                setThemen(data);
            } catch (err) {
                console.error(err);
                setError("Themen konnten nicht geladen werden.");
            } finally {
                setLoading(false);
            }
        }

        loadThemen();
    }, []);

    function handleSelectThema(id) {
        setSelectedThemaId(id);
        setResult(null);
    }

    function handleFinish(resultData) {
        setResult(resultData);
    }

    function handleRestart() {
        setResult(null);
        setSelectedThemaId(null);
    }

    return (
        <div className="app">
            <header className="app-header">
                <h1>IHK LernApp</h1>
                <p>Wähle ein Thema und starte dein Quiz.</p>
            </header>

            <main className="app-main">
                {loading && <p>Lade Themen...</p>}
                {error && <p className="error">{error}</p>}

                {!loading && !error && (
                    <>
                        <ThemeSelector
                            themen={themen}
                            selectedThemaId={selectedThemaId}
                            onSelect={handleSelectThema}
                        />

                        {!result && (
                            <QuizPage
                                themaId={selectedThemaId}
                                onFinish={handleFinish}
                            />
                        )}

                        {result && (
                            <div className="card" style={{ marginTop: "1.5rem" }}>
                                <h2>Ergebnis</h2>
                                <p>
                                    Richtig beantwortet: <strong>{result.correctAnswers}</strong> von{" "}
                                    <strong>{result.totalQuestions}</strong>
                                </p>
                                <p>
                                    Falsch beantwortet:{" "}
                                    <strong>{result.totalQuestions - result.correctAnswers}</strong>
                                </p>

                                <button onClick={handleRestart}>Quiz neu starten</button>
                            </div>
                        )}
                    </>
                )}
            </main>
        </div>
    );
}

export default App;