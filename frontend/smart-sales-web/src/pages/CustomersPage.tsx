
import { useEffect, useMemo, useState } from "react";
import { useNavigate, useLocation } from "react-router";
import {
    findAllCustomers,
    type Customer,
} from "../services/api";
import "./CustomersPage.css";

export default function CustomersPage() {
    const navigate = useNavigate();
    const location = useLocation();

    const [customers, setCustomers] = useState<Customer[]>([]);
    const [search, setSearch] = useState("");
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    const successMessage = (
        location.state as { success?: string } | null
    )?.success;

    useEffect(() => {
        async function loadCustomers() {
            try {
                setLoading(true);
                setError("");

                const data = await findAllCustomers();
                setCustomers(data);
            } catch (err) {
                setError(
                    err instanceof Error
                        ? err.message
                        : "Não foi possível carregar os clientes.",
                );
            } finally {
                setLoading(false);
            }
        }

        void loadCustomers();
    }, []);

    const filteredCustomers = useMemo(() => {
        const term = search.trim().toLowerCase();

        if (!term) return customers;

        return customers.filter((customer) =>
            [customer.name, customer.cpf, customer.email].some(
                (value) => value?.toLowerCase().includes(term),
            ),
        );
    }, [customers, search]);

    return (
        <main className="customers-page">
            <header className="customers-header">
                <div>
                    <h1>Clientes</h1>
                    <p>Gerencie os clientes cadastrados na loja.</p>
                </div>

                <button
                    className="customers-add-button"
                    onClick={() => navigate("/customers/new")}
                >
                    <span aria-hidden="true">+</span>
                    Novo cliente
                </button>
            </header>

            {successMessage && (
                <div className="customers-success" role="status">
                    {successMessage}
                </div>
            )}

            <section className="customers-summary">
                <div className="customers-summary-card">
                    <span>Total de clientes</span>
                    <strong>{customers.length}</strong>
                </div>

                <div className="customers-summary-card">
                    <span>Clientes encontrados</span>
                    <strong>{filteredCustomers.length}</strong>
                </div>
            </section>

            <section className="customers-content">
                <div className="customers-content-header">
                    <h2>Todos os clientes</h2>

                    <input
                        type="search"
                        className="customers-search"
                        placeholder="Buscar por nome, CPF ou e-mail..."
                        aria-label="Buscar clientes"
                        value={search}
                        onChange={(event) => setSearch(event.target.value)}
                    />
                </div>

                {loading ? (
                    <p className="customers-feedback">Carregando clientes...</p>
                ) : error ? (
                    <div className="customers-feedback customers-error">
                        <p>{error}</p>
                        <button onClick={() => window.location.reload()}>
                            Tentar novamente
                        </button>
                    </div>
                ) : filteredCustomers.length === 0 ? (
                    <div className="customers-empty">
                        <h3>
                            {search
                                ? "Nenhum cliente encontrado"
                                : "Nenhum cliente cadastrado"}
                        </h3>

                        <p>
                            {search
                                ? "Tente pesquisar com outros termos."
                                : "Cadastre o primeiro cliente para começar."}
                        </p>

                        {!search && (
                            <button
                                className="customers-add-button"
                                onClick={() => navigate("/customers/new")}
                            >
                                + Cadastrar primeiro cliente
                            </button>
                        )}
                    </div>
                ) : (
                    <div className="customer-table-container">
                        <table className="customer-table">
                            <thead>
                            <tr>
                                <th>Cliente</th>
                                <th>CPF</th>
                                <th>E-mail</th>
                                <th>Telefone</th>
                                <th>Nascimento</th>
                                <th>Status</th>
                            </tr>
                            </thead>

                            <tbody>
                            {filteredCustomers.map((customer) => (
                                <tr key={customer.id}>
                                    <td>
                                        <div className="customer-identity">
                        <span className="customer-avatar">
                          {customer.name?.charAt(0).toUpperCase() || "?"}
                        </span>
                                            <span className="customer-name">
                          {customer.name}
                        </span>
                                        </div>
                                    </td>
                                    <td>{customer.cpf}</td>
                                    <td>{customer.email}</td>
                                    <td>{customer.phone}</td>
                                    <td>
                                        {customer.birthDate
                                            ? new Date(
                                                `${customer.birthDate}T00:00:00`,
                                            ).toLocaleDateString("pt-BR")
                                            : "-"}
                                    </td>
                                    <td>
                      <span
                          className={`customer-status ${
                              customer.status === "ACTIVE"
                                  ? "customer-status-active"
                                  : "customer-status-inactive"
                          }`}
                      >
                        {customer.status === "ACTIVE"
                            ? "Ativo"
                            : customer.status || "Indefinido"}
                      </span>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </section>
        </main>
    );
}
