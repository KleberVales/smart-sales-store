
const API_BASE_URL =
    import.meta.env.VITE_API_BASE_URL || "http://localhost:8080";

export async function login(
    email: string,
    password: string,
): Promise<void> {
  const response = await fetch(`${API_BASE_URL}/api/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
    body: JSON.stringify({ email, password }),
  });

  if (!response.ok) {
    throw new Error(`Login failed: HTTP ${response.status}`);
  }
}

export interface Customer {
  id: number;
  name: string;
  cpf: string;
  email: string;
  phone: string;
  birthDate: string;
  status: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface CreateCustomerRequest {
  name: string;
  cpf: string;
  email: string;
  phone: string;
  birthDate: string;
}

export async function findAllCustomers(): Promise<Customer[]> {
  const response = await fetch(`${API_BASE_URL}/api/customers`, {
    headers: {
      Accept: "application/json",
    },
  });

  if (!response.ok) {
    throw new Error(`Erro ao listar clientes: HTTP ${response.status}`);
  }

  return response.json() as Promise<Customer[]>;
}

export async function createCustomer(
    customer: CreateCustomerRequest,
): Promise<void> {
  const response = await fetch(`${API_BASE_URL}/api/customers`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Accept: "application/json",
    },
    body: JSON.stringify(customer),
  });

  if (!response.ok) {
    const message = await response.text();
    throw new Error(
        message || `Erro ao cadastrar cliente: HTTP ${response.status}`,
    );
  }
}
