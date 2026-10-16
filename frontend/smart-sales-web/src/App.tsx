
import {
  BrowserRouter,
  Routes,
  Route,
  Navigate,
} from "react-router";

import LoginPage from "./pages/LoginPage";
import DashboardPage from "./pages/DashboardPage";
import ProductsPage from "./pages/ProductsPage";
import CustomersPage from "./pages/CustomersPage";
import CreateCustomerPage from "./pages/CreateCustomerPage";

export default function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/dashboard" element={<DashboardPage />} />
          <Route path="/products" element={<ProductsPage />} />

          <Route path="/customers" element={<CustomersPage />} />
          <Route
              path="/customers/new"
              element={<CreateCustomerPage />}
          />

          <Route
              path="/"
              element={<Navigate to="/login" replace />}
          />

          <Route
              path="*"
              element={<Navigate to="/login" replace />}
          />
        </Routes>
      </BrowserRouter>
  );
}
