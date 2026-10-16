
import { useState, type ChangeEvent, type FormEvent } from "react";
import { useNavigate } from "react-router";
import {
    createCustomer,
    type CreateCustomerRequest,
} from "../services/api";
import "./CreateCustomerPage.css";

const initialForm: CreateCustomerRequest = {
    name: "",
    cpf: "",
    email: "",
    phone: "",
    birthDate: "",
};

export default function CreateCustomerPage() {
    const navigate = useNavigate();

    const [form, setForm] =
        useState<CreateCustomerRequest>(initialForm);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    function handleChange(event: ChangeEvent<HTMLInputElement>) {
        const { name, value } = event.target;

        setForm((current) => ({
            ...current,
            [name]: value,
        }));
    }

    async function handleSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        setError("");
        setLoading(true);

        try {
            await createCustomer(form);

            navigate("/customers", {
                state: { success: "Cliente cadastrado com sucesso!" },
            });
        } catch (err) {
            setError(
                err instanceof Error
                    ? err.message
                    : "Não foi possível cadastrar o cliente.",
            );
        } finally {
            setLoading(false);
        }
    }

    return (
        <main className="create-customer-page">
            <header className="create-customer-header">
                <button
                    type="button"
                    className="create-customer-back"
                    onClick={() => navigate("/customers")}
                >
                    ← Voltar para clientes
                </button>

                <h1>Novo cliente</h1>
                <p>Preencha os dados para cadastrar um novo cliente.</p>
            </header>

            <form
                className="create-customer-form"
                onSubmit={handleSubmit}
            >
                <section className="create-customer-section">
                    <h2>Dados pessoais</h2>
                    <p>Informe os dados de identificação do cliente.</p>

                    <div className="create-customer-fields">
                        <label className="create-customer-field">
                            Nome completo *
                            <input
                                name="name"
                                value={form.name}
                                onChange={handleChange}
                                placeholder="Digite o nome completo"
                                required
                                maxLength={150}
                            />
                        </label>

                        <label className="create-customer-field">
                            CPF *
                            <input
                                name="cpf"
                                value={form.cpf}
                                onChange={handleChange}
                                placeholder="000.000.000-00"
                                required
                                maxLength={14}
                            />
                        </label>

                        <label className="create-customer-field">
                            E-mail *
                            <input
                                type="email"
                                name="email"
                                value={form.email}
                                onChange={handleChange}
                                placeholder="cliente@email.com"
                                required
                                maxLength={150}
                            />
                        </label>

                        <label className="create-customer-field">
                            Telefone *
                            <input
                                type="tel"
                                name="phone"
                                value={form.phone}
                                onChange={handleChange}
                                placeholder="(11) 99999-9999"
                                required
                                maxLength={20}
                            />
                        </label>

                        <label className="create-customer-field">
                            Data de nascimento *
                            <input
                                type="date"
                                name="birthDate"
                                value={form.birthDate}
                                onChange={handleChange}
                                required
                                max={new Date().toISOString().split("T")[0]}
                            />
                        </label>
                    </div>
                </section>

                {error && (
                    <div className="create-customer-error" role="alert">
                        {error}
                    </div>
                )}

                <footer className="create-customer-actions">
                    <button
                        type="button"
                        className="create-customer-cancel"
                        onClick={() => navigate("/customers")}
                        disabled={loading}
                    >
                        Cancelar
                    </button>

                    <button
                        type="submit"
                        className="create-customer-submit"
                        disabled={loading}
                    >
                        {loading ? "Cadastrando..." : "Cadastrar cliente"}
                    </button>
                </footer>
            </form>
        </main>
    );
}
