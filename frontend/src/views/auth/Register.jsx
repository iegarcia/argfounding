import { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  Button,
  Form,
  Container,
  Row,
  Col,
  Stack,
  Alert,
} from "react-bootstrap";
import { FaGoogle, FaFacebookSquare, FaApple } from "react-icons/fa";
import { useAuth } from "../../context/AuthContext";

const Register = () => {
  const { register } = useAuth();
  const navigate = useNavigate();

  const [newUser, setNewUser] = useState({
    name: "",
    surname: "",
    email: "",
    password: "",
    repeatPassword: "",
  });
  const [error, setError] = useState();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (newUser.repeatPassword === newUser.password) {
      try {
        await register(newUser);
        navigate("/");
      } catch (error) {
        console.log(error);
      }
    } else {
      setError("Las contraseñas no coinciden!");
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewUser({ ...newUser, [name]: value });
  };

  return (
    <div className="registro d-flex">
      {error && <Alert variant="primary">{error} </Alert>}

      <Container className="registro-container">
        <Row>
          <Col
            lg={6}
            style={{
              borderRight: "0.1rem solid",
              borderRightColor: "#00000042",
            }}
          >
            <h2 className="text-center mb-4">Regístrate con una red social</h2>
            <Stack gap={3} className="registro-content">
              <Button variant="info">
                <FaGoogle className="d-block mt-1 iconStyle" />
                Registrarse con Google
              </Button>
              <Button variant="info">
                <FaApple className="d-block mt-1 iconStyle" />
                Registrarse con Facebook
              </Button>
              <Button variant="info">
                <FaFacebookSquare className="d-block mt-1 iconStyle" />
                Registrarse con Apple
              </Button>
              <p>
                *Al registrarte, aceptas nuestras{" "}
                <strong>Condiciones de Servicio</strong> y reconoces haber leído
                nuestra <strong>Política de Privacidad</strong>
              </p>
            </Stack>
          </Col>
          <Col lg={6}>
            <h2 className="text-center">Regístrate con tu email</h2>
            <br />
            <Form onSubmit={handleSubmit}>
              <Stack gap={3} className="registro-content">
                <Form.Control
                  type="text"
                  name="name"
                  value={newUser.name}
                  placeholder="Nombre"
                  onChange={handleChange}
                  required
                />
                <Form.Control
                  type="text"
                  name="surname"
                  value={newUser.surname}
                  placeholder="Apellido"
                  onChange={handleChange}
                  required
                />
                <Form.Control
                  type="email"
                  name="email"
                  value={newUser.email}
                  placeholder="Email"
                  onChange={handleChange}
                  required
                />
                <Form.Control
                  type="password"
                  name="password"
                  value={newUser.password}
                  placeholder="Contraseña"
                  onChange={handleChange}
                  required
                />
                <Form.Control
                  type="password"
                  name="repeatPassword"
                  value={newUser.repeatPassword}
                  placeholder="Repetir contraseña"
                  onChange={handleChange}
                  required
                />

                <Button
                  variant="secondary"
                  type="submit"
                  onClick={handleSubmit}
                >
                  Crear cuenta
                </Button>
                <Form.Text className="text-muted">
                  *Al registrarte, aceptas nuestras{" "}
                  <strong>Condiciones de Servicio</strong> y reconoces haber
                  leído nuestra <strong>Política de Privacidad</strong>
                </Form.Text>
              </Stack>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Register;
