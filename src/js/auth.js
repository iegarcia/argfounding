//auth endpoints
import axios from "axios";

const urlApi = "http://167.99.235.152:8080/auth";

export const loginUser = async (data) => {
  const response = await axios.post(`${urlApi}/login`, data);
  return response.data.jwt;
};

export const createUser = async (newUser) => {
  const response = await axios.post(`${urlApi}/register`, newUser);
  return response.data;
};
