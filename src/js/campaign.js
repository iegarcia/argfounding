import axios from "axios";

const urlApi = "http://167.99.235.152:8080/campaigns";

const token = localStorage.getItem("token");
// const jwt = token.replace(/"/g, "");

let current_fs, next_fs, previous_fs;
let opacity, scale;
let animating;

export const addCampaigns = async (campaign) => {
  const data = new FormData();
  const jwt = token.replace(/"/g, "");

  for (const key in campaign) {
    data.append(key, campaign[key]);
  }
  const config = {
    method: "post",
    url: urlApi,
    headers: {
      "Content-Type": "application/json;",
      Authorization: `Bearer ${jwt}`,
    },
    data: data,
  };

  // const response = await axios(config);
  console.log(data);
};

export const getCampaigns = async () => {
  const response = await axios.get(`${urlApi}`);
  return response.data.content;
};

export const getCampaignById = async (id) => {
  const response = await axios.get(`${urlApi}/${id}`);
  return response.data;
};
export const getCampaignsByUser = async () => {
  const jwt = token.replace(/"/g, "");
  const config = {
    method: "get",

    url: "http://167.99.235.152:8080/profile/campaigns",
    headers: {
      "Content-Type": "application/json;",
      Authorization: `Bearer ${jwt}`,
    },
  };
  const response = await axios(config);
  console.log(response.data);
  return response.data;
};

export const getCampaignComments = async (campaignId) => {
  const response = await axios.get(`${urlApi}/${campaignId}/comments`);
  return response.data;
};

export const deleteCampaign = async (campaignId) => {
  const response = await axios.delete(`${urlApi}/${campaignId}`);
};

export const nextLayout = () => {
  const nextBtns = document.querySelectorAll(".next");
  nextBtns.forEach(function (button) {
    button.addEventListener("click", function () {
      if (animating) return false;
      animating = true;

      current_fs = this.parentElement;
      next_fs = this.parentElement.nextElementSibling;

      const progressLi = Array.from(
        document.querySelectorAll("#progressbar li")
      );
      const fieldset = Array.from(document.querySelectorAll("fieldset"));
      progressLi[fieldset.indexOf(next_fs)].classList.add("active");

      next_fs.style.display = "block";

      let duration = 800;
      let startTime = null;
      function step(currentTime) {
        if (!startTime) startTime = currentTime;
        let progress = currentTime - startTime;
        opacity = 1 - progress / duration;
        scale = 1 - (1 - opacity) * 0.2;

        current_fs.style.opacity = opacity;
        current_fs.style.transform = "scale(" + scale + ")";

        next_fs.style.opacity = 1 - opacity;

        if (progress < duration) {
          window.requestAnimationFrame(step);
        } else {
          current_fs.style.display = "none";
          animating = false;
        }
      }
      window.requestAnimationFrame(step);
    });
  });
};

export const previousLayout = () => {
  const previousBtns = document.querySelectorAll(".previous");
  previousBtns.forEach(function (btn) {
    btn.addEventListener("click", function () {
      if (animating) return false;
      animating = true;

      const current_fs = this.parentElement;
      const previous_fs = this.parentElement.previousElementSibling;

      const progressLi = Array.from(
        document.querySelectorAll("#progressbar li")
      );
      const fieldset = Array.from(document.querySelectorAll("fieldset"));
      progressLi[fieldset.indexOf(current_fs)].classList.remove("active");

      previous_fs.style.display = "block";

      const start = performance.now();
      const duration = 300;

      function update(currentTime) {
        const elapsed = currentTime - start;
        const progress = elapsed / duration;

        if (progress > 1) {
          current_fs.style.display = "none";
          animating = false;
          return;
        }

        previous_fs.style.transform = `none`;
        previous_fs.style.opacity = "unset";
        window.requestAnimationFrame(update);
      }

      window.requestAnimationFrame(update);
    });
  });
};
