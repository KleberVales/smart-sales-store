
import AppLayout from "../components/layout/AppLayout";
import MetricCard from "../components/dashboard/MetricCard";

export default function DashboardPage() {
  return (
    <AppLayout>
      <section className="dashboard-content">
        <div className="metrics-grid">
          <MetricCard
            title="Total Revenue"
            value="R$ 0,00"
            description="Total sales revenue"
          />

          <MetricCard
            title="Orders"
            value="0"
            description="Total orders"
          />

          <MetricCard
            title="Customers"
            value="0"
            description="Registered customers"
          />

          <MetricCard
            title="Products"
            value="0"
            description="Products registered"
          />
        </div>

        <section className="recent-sales">
          <h2>Recent Sales</h2>
          <p>No sales to display yet.</p>
        </section>
      </section>
    </AppLayout>
  );
}