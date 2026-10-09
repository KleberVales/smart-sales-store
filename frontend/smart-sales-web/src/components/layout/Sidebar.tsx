
const menuItems = [
  { label: "Dashboard", icon: "▦" },
  { label: "Products", icon: "□" },
  { label: "Customers", icon: "♙" },
  { label: "Sales", icon: "🛒" },
  { label: "Inventory", icon: "▤" },
];

export default function Sidebar() {
  return (
    <aside className="sidebar">
      <h2 className="brand">Smart Sales</h2>

      <nav>
        {menuItems.map((item, index) => (
          <a
            key={item.label}
            href={index === 0 ? "/" : `/${item.label.toLowerCase()}`}
            className={`nav-item ${index === 0 ? "active" : ""}`}
          >
            <span>{item.icon}</span>
            {item.label}
          </a>
        ))}
      </nav>
    </aside>
  );
}