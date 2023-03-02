import { Navigate } from "react-router-dom";

export function ProtectedRoutes({ children }) {
  const token = localStorage.getItem("token");
  if (token === null) return <Navigate to="/login" />;

  return <>{children} </>;
}
