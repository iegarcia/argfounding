import { FaFacebook, FaInstagram, FaTwitter } from "react-icons/fa";
import Logo from "../assets/logo.svg";

const footerLinks = [
  "Info",
  "Support",
  "Marketing",
  "Terms of Use",
  "Privacy",
  "Policy",
];
const iconSize = 30;

const Footer = () => {
  return (
    <div className="footer">
      <div className="d-flex justify-content-center align-items-center">
        <div className="divider" style={{ marginRight: "3rem" }}></div>
        <div className="footer-social">
          <FaFacebook color="black" size={iconSize} />
          <FaInstagram color="black" size={iconSize} />
          <FaTwitter color="black" size={iconSize} />
        </div>

        <div className="divider" style={{ marginLeft: "3rem" }}></div>
      </div>
      <div className="d-flex flex-column align-items-center">
        <img className="mt-4" src={Logo} alt="logo" style={{ width: "7rem" }} />
        <ul className="footer-link-container mt-4">
          {footerLinks.map((fl, idx) => {
            return (
              <li className="text-center footer-link" key={idx}>
                {fl}
              </li>
            );
          })}
        </ul>
      </div>
    </div>
  );
};

export default Footer;
