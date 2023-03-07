import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import CampaignEdit from "../../components/CampaignEdit";
import CampaignForm from "../../components/CampaignForm";

import { getCampaignById, nextLayout, previousLayout } from "../../js/campaign";

const AddCampaign = ({ editar }) => {
  const [campaignData, setCampaignData] = useState([]);

  const location = useLocation();
  const id = location.pathname.slice(6);

  const getCampaign = async (id) => {
    const campaign = await getCampaignById(id);
    setCampaignData(campaign);
  };

  useEffect(() => {
    nextLayout();
    previousLayout();

    async function run() {
      await getCampaign(id);
    }

    if (editar) {
      run();
    }
  }, [id]);

  return (
    <>{editar ? <CampaignEdit data={campaignData} /> : <CampaignForm />}</>
  );
};

export default AddCampaign;
