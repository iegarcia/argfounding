import { useEffect, useState } from "react";
import { Accordion, Button, Modal } from "react-bootstrap";
import { deleteCampaign, getCampaignsByUser } from "../../js/campaign";

const Profile = () => {
  const [userCampaigns, setUserCampaigns] = useState([]);
  const [show, setShow] = useState(false);
  const [cmpId, setCmpId] = useState("");

  const handleClose = () => setShow(false);

  const handleShow = (id) => {
    setShow(true);
    setCmpId(id);
  };

  const handleDelete = async () => {
    try {
      await deleteCampaign(cmpId);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    async function run() {
      const cmp = await getCampaignsByUser();
      setUserCampaigns(cmp);
    }

    run();
  }, []);
  return (
    <>
      <Accordion defaultActiveKey="0">
        {userCampaigns.map((c, i) => {
          return (
            <Accordion.Item eventKey={i} key={i}>
              <Accordion.Header>{c.name}</Accordion.Header>
              <Accordion.Body>
                {c.shortDescription}
                <div className="d-flex w-100 justify-content-end">
                  <a href={`/campania/${c.campaignId}`}>
                    <Button variant="info">Ver</Button>
                  </a>
                  &nbsp; &nbsp;
                  <a href={`/edit/${c.campaignId}`}>
                    <Button variant="warning">Editar</Button>
                  </a>
                  &nbsp; &nbsp;
                  <Button
                    variant="danger"
                    onClick={() => handleShow(c.campaignId)}
                  >
                    Eliminar
                  </Button>
                </div>
              </Accordion.Body>
            </Accordion.Item>
          );
        })}
      </Accordion>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header>
          <Modal.Title>Eliminar campaña</Modal.Title>
        </Modal.Header>
        <Modal.Body className="text-center">
          ¿Estas seguro que queres eliminar la campaña?
          <br />
          <strong>(Esta accion es irreversible)</strong>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="danger" onClick={handleDelete}>
            Eliminar
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
};

export default Profile;
