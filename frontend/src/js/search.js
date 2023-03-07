// search endpoints
import axios from "axios";

const urlApi = "http://167.99.235.152:8080/campaigns";

export const searchCampaigns = async (key) => {
  const response = await axios.get(`${urlApi}/search?keyword=${key}`);
  // const response = await axios.get(`${urlApi}/search?keyword=producto`);
  console.log(response.data)
  return response.data;
};
export const searchCampaignsByKey = async (key) => {
  const response = await axios.get(`${urlApi}/search/${key}`);
  return response.data;
};
