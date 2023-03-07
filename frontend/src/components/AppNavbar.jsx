import { Button, Container, Nav, Navbar, NavDropdown } from "react-bootstrap";
// import Logo from "../assets/logo.svg";
import { useAuth } from "../context/AuthContext";

import { useState } from "react";

const token = localStorage.getItem("token");

const AppNavbar = () => {
  const { logout } = useAuth();
  const [search, setSearch] = useState("");

  const handleLogout = () => {
    logout();
    window.location.reload();
  };

  const handleChange = (event) => {
    setSearch(event.target.value);
  };

  return (
    <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand href="/">
          <h2>ArgFounding</h2>
          {/* <img src={Logo} alt="logo" /> */}
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse className="justify-content-end" id="basic-navbar-nav">
          <Nav className="justify-content-around" style={{ width: "60%" }}>
            {token === null ? (
              <>
                <Nav.Link style={{ fontSize: "15px" }} href="/login">
                  Login
                </Nav.Link>
                <Nav.Link style={{ fontSize: "15px" }} href="/registro">
                  Registro
                </Nav.Link>
              </>
            ) : (
              <>
                <div className="d-flex justify-content-around">
                  <form
                    className="d-flex"
                    style={{
                      borderRadius: "20px",
                      width: "100%",
                      height: "95%",
                      position: "relative", // Añadimos posición relativa al contenedor
                    }}
                    role="search"
                  >
                    <div
                      style={{
                        position: "absolute",
                        top: "50%",
                        transform: "translateY(-50%)",
                      }}
                    ></div>
                    <input
                      className="inputOne form-control text-center mt-1"
                      type="search"
                      placeholder="Buscador"
                      onChange={handleChange}
                      style={{ paddingLeft: "30px" }} // Añadimos padding para que el texto no se solape con el icono
                    />
                    <Nav.Link
                      href={`/buscar?q=${search}`}
                      className="buttonSearch d-inline"
                      style={{ display: "none" }} // Escondemos el link que no necesitamos
                    >
                      <svg
                        className="svg-icon search-icon"
                        aria-labelledby="title desc"
                        role="img"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 19.9 19.7"
                        style={{ width: "20px", height: "20px" }} // Añadimos tamaño al icono
                      >
                        <title id="title">Search Icon</title>
                        <desc id="desc">A magnifying glass icon.</desc>
                        <g className="search-path" fill="none" stroke="#848F91">
                          <path
                            strokeLinecap="square"
                            d="M18.5 18.3l-5.4-5.4"
                          />
                          <circle cx="8" cy="8" r="7" />
                        </g>
                      </svg>
                    </Nav.Link>
                  </form>
                </div>
                <Nav.Link style={{ fontSize: "medium" }} href="/favorites">
                  Favoritos
                </Nav.Link>
                <Nav.Link style={{ fontSize: "medium" }} href="/mensajes">
                  Mensajes
                </Nav.Link>
                <NavDropdown
                  style={{ fontSize: "medium" }}
                  title="Perfil"
                  id="basic-nav-dropdown"
                >
                  <NavDropdown.Item
                    style={{ fontSize: "smaller" }}
                    href="/perfil"
                  >
                    Campañas creadas
                  </NavDropdown.Item>
                  <NavDropdown.Item
                    style={{ fontSize: "medium" }}
                    href="#action/3.2"
                  >
                    Mis patrocinios
                  </NavDropdown.Item>
                  <NavDropdown.Divider />
                  <NavDropdown.Item
                    style={{ fontSize: "medium" }}
                    href="#action/3.3"
                  >
                    Mensajes
                  </NavDropdown.Item>
                  <NavDropdown.Item
                    style={{ fontSize: "medium" }}
                    href="#action/3.4"
                  >
                    Configuraciones
                  </NavDropdown.Item>
                  <NavDropdown.Item
                    style={{ fontSize: "medium" }}
                    href="#"
                    onClick={handleLogout}
                  >
                    Log Out
                  </NavDropdown.Item>
                </NavDropdown>
                <a href="/add">
                  <Button
                    variant="primary"
                    style={{
                      borderRadius: "20px",
                      width: "120%",
                      height: "95%",
                    }}
                  >
                    Cargar proyecto
                  </Button>
                </a>
              </>
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default AppNavbar;
