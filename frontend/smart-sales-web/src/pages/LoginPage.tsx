
export default function LoginPage() {
  return (
    <div style={{ padding: "40px", fontFamily: "Arial" }}>
      <h1>Smart Sales Store</h1>
      <h2>Login</h2>

      <form>
        <div style={{ marginBottom: "16px" }}>
          <label htmlFor="email">Email</label>
          <br />
          <input
            id="email"
            type="email"
            placeholder="Enter your email"
          />
        </div>

        <div style={{ marginBottom: "16px" }}>
          <label htmlFor="password">Password</label>
          <br />
          <input
            id="password"
            type="password"
            placeholder="Enter your password"
          />
        </div>

        <button type="submit">Sign in</button>
      </form>
    </div>
  );
}