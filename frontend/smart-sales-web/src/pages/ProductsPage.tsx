import { useEffect, useMemo, useState } from "react";
import "./ProductsPage.css";

type Product = {
    id: number;
    name: string;
    description: string;
    price: number;
    stockQuantity: number;
    active: boolean;
};

const API_URL = "http://localhost:8080/api/products";

export default function ProductsPage() {
    const [products, setProducts] = useState<Product[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);
    const [search, setSearch] = useState("");

    useEffect(() => {
        const controller = new AbortController();

        async function fetchProducts() {
            try {
                setLoading(true);
                setError(null);

                const response = await fetch(API_URL, {
                    method: "GET",
                    headers: {
                        Accept: "application/json",
                    },
                    signal: controller.signal,
                });

                if (!response.ok) {
                    throw new Error(
                        `Erro ao buscar produtos: HTTP ${response.status}`
                    );
                }

                const data: Product[] = await response.json();
                setProducts(data);
            } catch (err) {
                if (err instanceof Error && err.name === "AbortError") {
                    return;
                }

                setError(
                    err instanceof Error
                        ? err.message
                        : "Não foi possível carregar os produtos."
                );
            } finally {
                if (!controller.signal.aborted) {
                    setLoading(false);
                }
            }
        }

        fetchProducts();

        return () => controller.abort();
    }, []);

    const filteredProducts = useMemo(() => {
        const term = search.trim().toLowerCase();

        if (!term) {
            return products;
        }

        return products.filter(
            (product) =>
                product.name?.toLowerCase().includes(term) ||
                product.description?.toLowerCase().includes(term)
        );
    }, [products, search]);

    const formatCurrency = (price: number) =>
        new Intl.NumberFormat("pt-BR", {
            style: "currency",
            currency: "BRL",
        }).format(price);

    if (loading) {
        return (
            <main className="products-page">
                <h1>Produtos</h1>
                <p>Carregando produtos...</p>
            </main>
        );
    }

    if (error) {
        return (
            <main className="products-page">
                <h1>Produtos</h1>
                <p className="products-error">{error}</p>

                <button
                    type="button"
                    onClick={() => window.location.reload()}
                >
                    Tentar novamente
                </button>
            </main>
        );
    }

    return (
        <main className="products-page">
            <header className="products-header">
                <div>
                    <h1>Produtos</h1>
                    <p>Gerencie os produtos da sua loja.</p>
                </div>

                <span className="products-count">
          {products.length} produto(s)
        </span>
            </header>

            <section className="products-toolbar">
                <input
                    type="search"
                    placeholder="Pesquisar por nome ou descrição..."
                    value={search}
                    onChange={(event) => setSearch(event.target.value)}
                />
            </section>

            <section className="products-table-container">
                <table className="products-table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Produto</th>
                        <th>Preço</th>
                        <th>Estoque</th>
                        <th>Status</th>
                    </tr>
                    </thead>

                    <tbody>
                    {filteredProducts.map((product) => (
                        <tr key={product.id}>
                            <td>{product.id}</td>

                            <td>
                                <strong>{product.name}</strong>
                                <small>
                                    {product.description || "Sem descrição"}
                                </small>
                            </td>

                            <td>{formatCurrency(product.price)}</td>

                            <td>
                  <span
                      className={
                          product.stockQuantity === 0
                              ? "stock-empty"
                              : product.stockQuantity < 10
                                  ? "stock-low"
                                  : ""
                      }
                  >
                    {product.stockQuantity}
                  </span>
                            </td>

                            <td>
                  <span
                      className={
                          product.active
                              ? "product-status active"
                              : "product-status inactive"
                      }
                  >
                    {product.active ? "Ativo" : "Inativo"}
                  </span>
                            </td>
                        </tr>
                    ))}

                    {filteredProducts.length === 0 && (
                        <tr>
                            <td colSpan={5} className="products-empty">
                                {search
                                    ? "Nenhum produto encontrado para essa pesquisa."
                                    : "Nenhum produto cadastrado."}
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </section>
        </main>
    );
}