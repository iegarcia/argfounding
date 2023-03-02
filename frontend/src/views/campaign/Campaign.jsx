import { useState, useEffect } from "react";
import { Card, ProgressBar, Spinner } from "react-bootstrap";
import { useLocation } from "react-router-dom";
import { getCampaignById, getCampaignComments } from "../../js/campaign";
import { FaUserAlt } from "react-icons/fa";
import Comments from "../../components/Comments";

const Campaign = () => {
  const [camData, setCamData] = useState([]);
  const [comments, setComments] = useState([]);

  const [name, setName] = useState("");
  const [percent, setPercent] = useState(0);

  const location = useLocation();
  const id = location.pathname.slice(10);

  useEffect(() => {
    async function run() {
      const campaignDetails = await getCampaignById(id);
      const campaignComments = await getCampaignComments(id);

      setCamData(campaignDetails);
      setComments(campaignComments);

      const { firstName, lastName } = campaignDetails.creator;
      const { currentMoney, goalMoney } = campaignDetails;

      const progress = (currentMoney * 100) / goalMoney;

      setName(firstName + " " + lastName);
      setPercent(progress);
    }

    run();
  }, [id]);

  return (
    <div className="campaign">
      {camData.donationTiers === undefined ? (
        <Spinner animation="border" role="status">
          <span className="visually-hidden">Loading...</span>
        </Spinner>
      ) : (
        <>
          <div
            className="campaign-header d-block"
            style={{ backgroundImage: `url('${camData.bannerUrl}')` }}
          >
            <br />
            <br />
            <ProgressBar className="campaign-progress" now={percent} />
            <h5 className="campaign-money mt-2 text-light">
              {camData.currentMoney}/{camData.goalMoney}
            </h5>
            <div className="campaign-header-title ">
              <h2 className="text-light">{camData.name}</h2>
              <p className="text-light">{camData.shortDescription}</p>
            </div>
          </div>

          <div className="row">
            <div className="col-md-8">
              <div className="campaign-info-container mt-3">
                <div className="owner-data">
                  <img
                    className="owner-image"
                    src={camData.logoUrl}
                    alt="profile-picture"
                  />
                  <p
                    className="campaign-subtitle "
                    style={{ float: "right", marginLeft: "0.5rem" }}
                  >
                    Campaña de <br />
                    <span className="campaign-text">{name}</span>
                  </p>
                </div>

                <div className="campaign-category">
                  <p className="campaign-subtitle">
                    Categoria
                    <br />
                    <span className="campaign-text">Producto</span>
                  </p>
                </div>
                <div className="campaign-followers">
                  <p className="campaign-text">
                    100
                    <span className="campaign-subtitle">
                      Patrocinadores <br />
                      en el proyecto
                    </span>
                  </p>
                </div>
                <div className="campaign-time text-center">
                  <span className="campaign-text">{camData.daysLeft}</span>
                  <br />
                  <span className="campaign-subtitle">Dias</span>
                </div>
                <span className="campaign-subtitle text-center">
                  Agregar a <br />
                  favoritos
                </span>
              </div>
              <div className="campaign-details text-center">
                <div className="campaign-details-content">
                  <h3 className="campaign-details-title">
                    Sobre nuestro proyecto
                  </h3>
                  <p className="mb-4 campaign-details-text">
                    {camData.longDescription}
                  </p>
                  {camData.donationTiers.map((e) => {
                    return (
                      <>
                        <img
                          src={e.imageUrl}
                          alt={e.name}
                          width={300}
                          height={200}
                        />
                        <br />
                        <br />
                      </>
                    );
                  })}
                </div>
                <br />
              </div>
              <a className="campaign-security mt-3" href="#">
                Denunciar este proyecto
              </a>
            </div>
            <div className="col-md-3 ">
              {camData.donationTiers.map((e, idx) => {
                return (
                  <Card
                    key={idx}
                    className="campaign-rewards"
                    style={{ borderBottom: "9px solid #009fe3" }}
                  >
                    <div
                      className="campaign-rewards-header"
                      style={{
                        backgroundImage: `url('${e.imageUrl}')`,
                      }}
                    >
                      <h2 className="text-light campaign-rewards-title">
                        0{e.tierId}
                      </h2>
                    </div>
                    <Card.Body style={{ padding: "0.5rem" }}>
                      <Card.Title className="text-center">
                        {e.tierName}
                      </Card.Title>
                      <div style={{ fontSize: "xx-small" }}>
                        {e.description}
                        <div className="mt-2" style={{ float: "right" }}>
                          <span className="campaign-rewards-price">
                            ${e.price}
                          </span>
                          <br />
                          <span className="campaign-rewards-followers mt-2">
                            10
                            {/* {e.followers} */}
                            &nbsp;
                            <FaUserAlt />
                          </span>
                        </div>
                      </div>
                    </Card.Body>
                  </Card>
                );
              })}
            </div>
          </div>

          <Comments content={comments} />
        </>
      )}
    </div>
  );
};

export default Campaign;
