import { useEffect, useState } from "react";
import { Carousel } from "react-bootstrap";
import { getCampaigns } from "../js/campaign";
// import CampaignPopulars from "../components/Campaings/CampaignPopulars";
// import CampaignsNewest from "../components/Campaings/Campaignewst";
import CampaignSlider from "../components/CampaignSlider";

const Home = () => {
  const [sliderData, setSliderData] = useState([]);

  useEffect(() => {
    async function run() {
      const homeCampaigns = await getCampaigns();
      const slider = homeCampaigns.slice(1);
      setSliderData(slider);
    }

    run();
  }, []);

  return (
    <>
      <Carousel controls={true} indicators={false}>
        {sliderData.map((sl, idx) => {
          return (
            <Carousel.Item key={idx}>
              <img
                className="d-block w-100"
                src={sl.bannerUrl}
                alt={`imagen-${idx}`}
                // width={800}
                // height={400}
              />
              <Carousel.Caption>
                <h3>{sl.name}</h3>
                <p>{sl.shortDescription}</p>
              </Carousel.Caption>
            </Carousel.Item>
          );
        })}
      </Carousel>
      <hr />
      <h3>Mas recientes</h3>
      <CampaignSlider data={"newest"} />
      <br />
      <h3>Mas Populares</h3>
      <CampaignSlider data={"mostPopular"} />

      <br />
    </>
  );
};

export default Home;
