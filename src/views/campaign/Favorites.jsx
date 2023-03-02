import React from "react";
import { useFavorite } from "../../context/FavoriteContext";

import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";

function Favorites() {
  const { favorites } = useFavorite();
  console.log(favorites);

  return (
    <>
      <Navbar bg="light" className="d-flex flex-column mb-2 border-0">
        <Container>
          <Navbar.Brand>Favorites</Navbar.Brand>
          <button className=" bg-transparent text-dark border-0 ">
            Eliminar todos los favoritos
          </button>
        </Container>
      </Navbar>

      <Card style={{ width: "18rem" }}>
        <Card.Img
          className="d-block w-100"
          src="https://dummyimage.com/500x200/a3a3a3/fff.png&text=image1"
          alt="Imagen 1"
        />
        <Card.Body>
          <Card.Title>{favorites.proyectname} </Card.Title>
          <Card.Subtitle> {favorites.pymesname} </Card.Subtitle>
          <Card.Text>
            {favorites.containertext} {favorites.money}
          </Card.Text>
          <Button variant="secondary">
            <svg
              width="20px"
              height="20px"
              viewBox="0 0 24 24"
              stroke-width="0.5"
              fill="none"
              xmlns="http://www.w3.org/2000/svg"
              color="#000000"
            >
              <path
                d="M20 9l-1.995 11.346A2 2 0 0116.035 22h-8.07a2 2 0 01-1.97-1.654L4 9M21 6h-5.625M3 6h5.625m0 0V4a2 2 0 012-2h2.75a2 2 0 012 2v2m-6.75 0h6.75"
                stroke="#000000"
              ></path>
            </svg>
          </Button>
        </Card.Body>
      </Card>
    </>
  );
}

export default Favorites;
