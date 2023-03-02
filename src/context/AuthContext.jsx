import { createContext, useContext, useState } from "react";
import { createUser, loginUser } from "../js/auth";

export const authContext = createContext();

export const useAuth = () => {
  const context = useContext(authContext);
  if (!context) throw new Error("No auth provider");
  return context;
};

function AuthProvider({ children }) {
  const [user, setUser] = useState({
    email: "",
    password: "",
  });

  const login = async (userData) => {
    try {
      const userToken = await loginUser(userData);
      setUser(userData);
      localStorage.setItem("token", userToken);
    } catch (error) {
      throw new Error(error);
    }
  };

  const register = async (newUser) => {
    try {
      const userToken = await createUser(newUser);
    } catch (error) {
      throw new Error(error);
    }
  };

  const logout = () => {
    localStorage.clear();
  };

  return (
    <authContext.Provider value={{ login, register, user, logout }}>
      {children}
    </authContext.Provider>
  );
}

export default AuthProvider;
