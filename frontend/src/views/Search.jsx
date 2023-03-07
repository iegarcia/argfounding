import { searchCampaigns, searchCampaignsByKey } from "../js/search";
import { useState } from "react";
import React from "react";

import { Card, ProgressBar, Button } from "react-bootstrap";

import { useLocation } from "react-router-dom";

const Searchs = () => {
  const [campanas, setCampanas] = useState([]);

  const location = useLocation();
  const item = location.search.slice(3);
  const [search, setSearch] = useState(item);

  const handledSubmit = async (event) => {
    event.preventDefault();

    const results = await searchCampaigns(search);
    setCampanas(results);
    console.log(results);
  };

  const searchByKey = async (key) => {
    setCampanas([]);
    const results = await searchCampaignsByKey(key);
    setCampanas(results);
    console.log(results);
  };

  const handledChange = (event) => {
    setSearch(event.target.value);
  };

  return (
    <div className="search d-flex ">
      <div className="containerSearch">
        <h1>Descubre nuevos proyectos</h1>
        <h2>o explora por categoria</h2>
        <div className="containerButton d-flex justify-content-center mb-4 mt-5 gap-3">
          <button onClick={() => searchByKey("products")}>Productos</button>
          <button onClick={() => searchByKey("services")}>Servicios</button>
          <button onClick={() => searchByKey("newest")}>Recientes</button>
          <button onClick={() => searchByKey("mostPopular")}>
            Popularidad
          </button>
          <button onClick={() => searchByKey("nearGoal")}>Aleatorio</button>
        </div>
        <div className="d-flex justify-content-center ">
          <form
            className="form d-flex mt-2 mb-4"
            onSubmit={handledSubmit}
            role="search"
          >
            <input
              onChange={handledChange}
              value={search}
              className="form-control me-2 "
              type="search"
              placeholder="Buscar proyecto"
              aria-label="Search"
            />
            <Button variant="info" type="submit" className="d-block iconStyle">
              <svg
                className="svg-icon search-icon"
                aria-labelledby="title desc"
                role="img"
                xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 19.9 19.7"
              >
                <title id="title">Search Icon</title>
                <desc id="desc">A magnifying glass icon.</desc>
                <g className="search-path" fill="none" stroke="#848F91">
                  <path strokeLinecap="square" d="M18.5 18.3l-5.4-5.4" />
                  <circle cx="8" cy="8" r="7" />
                </g>
              </svg>
            </Button>
          </form>
        </div>

        <div>
          <h4 className="mt-4 mb-5"> Quizas te interese... </h4>
          {campanas.length === 0 ? (
            <p>No se encuentran campañas</p>
          ) : (
            <div className="d-flex justify-content-between flex-wrap">
              {campanas.map((e, idx) => {
                return (
                  <Card style={{ width: "18rem" }} className="mb-4">
                    <Card.Img
                      variant="top"
                      src={e.logoUrl}
                      width={286}
                      height={162}
                    />
                    <ProgressBar
                      className="campaign-progress"
                      now={(e.currentMoney * 100) / e.goalMoney}
                    />
                    <Card.Body>
                      <Card.Title>{e.name}</Card.Title>
                      <Card.Text>{e.shortDescription}</Card.Text>
                      <p>
                        ${e.currentMoney} / {e.goalMoney}
                      </p>

                      <a href={`/campania/${e.campaignId}`}>ver mas</a>
                    </Card.Body>
                  </Card>
                );
              })}
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default Searchs;
