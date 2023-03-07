import { useState } from "react";
import { useNavigate } from "react-router-dom";

import { Alert, Button, Form, Spinner, Stack } from "react-bootstrap";

import { useAuth } from "../../context/AuthContext";

const Login = () => {
  const { login } = useAuth();
  const navigate = useNavigate();

  const [user, setUser] = useState({
    email: "",
    password: "",
  });

  const [error, setError] = useState();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const loadingBtn = document.getElementById("loadingBtn");
    const loginBtn = document.getElementById("loginBtn");

    try {
      loadingBtn.style = "display: initial";
      loginBtn.style = "display: none";
      await login(user);
      setTimeout(() => {
        loadingBtn.style = "display: none";
        loginBtn.style = "display: initial";
        navigate("/");
        window.location.reload();
      }, 3000);
    } catch (error) {
      setError("Usuario o clave incorrecta");
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({ ...user, [name]: value });
  };

  return (
    <div className="login d-flex">
      <div className="login-container">
        {error && <Alert variant="primary">{error} </Alert>}
        <h2>Inicia sesión</h2>
        <div className="login-social-container ">
          <Stack gap={3}>
            <Button variant="info">Registrarse con Google</Button>
            <Button variant="info">Registrarse con Facebook</Button>
            <Button variant="info">Registrarse con Apple</Button>
          </Stack>
          <Form onSubmit={handleSubmit}>
            <Form.Control
              type="email"
              placeholder="Email"
              value={user.email}
              name="email"
              onChange={handleChange}
              required
              className="mb-2 mt-2"
            />
            <Form.Control
              type="password"
              placeholder="Contraseña"
              value={user.password}
              name="password"
              onChange={handleChange}
              required
            />
            <Form.Group>
              <Form.Check type="checkbox" label="Recordarme" />
              <Form.Text className="text-muted">
                <a href="/login">¿Olvido su contraseña?</a>
              </Form.Text>
            </Form.Group>
            <br />
            <Button
              id="loadingBtn"
              variant="secondary"
              disabled
              style={{ display: "none" }}
            >
              <Spinner
                as="span"
                animation="border"
                size="sm"
                role="status"
                aria-hidden="true"
              />
              Iniciar sesión
              <span className="visually-hidden">Loading...</span>
            </Button>
            <Button id="loginBtn" variant="secondary" type="submit">
              Iniciar sesión
            </Button>
          </Form>
        </div>
        <br />
        <span>
          ¿Aún no tenes usuario? <a href="/registro">Regístrate!</a>
        </span>
      </div>
    </div>
  );
};

export default Login;
