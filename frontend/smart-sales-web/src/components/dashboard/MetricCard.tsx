
type MetricCardProps = {
  title: string;
  value: string;
  description: string;
};

export default function MetricCard({
  title,
  value,
  description,
}: MetricCardProps) {
  return (
    <article className="metric-card">
      <p className="metric-title">{title}</p>
      <h2>{value}</h2>
      <p className="metric-description">{description}</p>
    </article>
  );
}