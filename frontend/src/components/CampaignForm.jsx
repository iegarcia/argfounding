import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import ReactQuill from "react-quill";

import { addCampaigns } from "../js/campaign";
const CampaignForm = () => {
  const [formData, setFormData] = useState({
    name: "",
    image: "",
    category: "",
    goalMoney: "",
    closingDate: "",
    shortDescription: "",
    longDescription: "",
  });
  const [check, setCheck] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };
  const handleChangeEditor = (e) => {
    setFormData({ ...formData, longDescription: e });
  };
  const handleCheck = (e) => {
    setCheck(e.target.labels[0].textContent);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setFormData({ ...formData, category: check });

    try {
      await addCampaigns(formData);
    } catch (error) {
      // console.log(error);
    }
  };

  return (
    <Form id="msform" onSubmit={handleSubmit}>
      <ul id="progressbar">
        <li className="active"></li>
        <li></li>
        <li></li>
      </ul>

      <fieldset id="first">
        <h2 className="fs-title">Cargar campaña</h2>
        <h3 className="fs-subtitle">Aca empieza la aventura!!!</h3>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Elegí un titulo para tu campaña</Form.Label>
          <Form.Control
            type="text"
            name="name"
            placeholder="Ingresa un titulo llamativo"
            onChange={handleChange}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Descripcion breve</Form.Label>
          <Form.Control
            type="text"
            name="shortDescription"
            placeholder="Breve descripción de la campaña"
            onChange={handleChange}
          />
        </Form.Group>
        <Form.Group className="mb-3">
          <Form.Check
            type="radio"
            name="category"
            id="categoryService"
            label="Servicio"
            onClick={handleCheck}
          />
          <Form.Check
            type="radio"
            name="category"
            id="categoryProd"
            label="Producto"
            onClick={handleCheck}
          />
        </Form.Group>
        {/* <Form.Group controlId="formFile" className="mb-3">
          <Form.Label>Imagen (Esta imagen se utilizara como banner)</Form.Label>
                  <Form.Control name="image" type="file" onChange={handleChange}>
        </Form.Group> */}
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Fecha Limite</Form.Label>
          <Form.Control
            type="date"
            name="closingDate"
            onChange={handleChange}
          />
          <Form.Label>Monto a recaudar</Form.Label>
          <Form.Control type="text" name="goalMoney" onChange={handleChange} />
        </Form.Group>

        <Button type="button" name="next" className="next action-button">
          Next
        </Button>
      </fieldset>
      <fieldset id="second">
        <h2 className="fs-title">Contenido de la campaña</h2>
        <h3 className="fs-subtitle">
          En esta parte, pode explicar mas a tus futuros inversores sobre tu
          proyecto, porque lo haces, que beneficios va a traer. Recordá que es
          importante que te explayes y seas lo mas claro y sincero posible,
          porque de eso depende que tu campaña triunfe.
        </h3>
        <ReactQuill
          theme="snow"
          name="longDescription"
          onChange={handleChangeEditor}
        />
        <Button
          type="button"
          name="previous"
          className="previous action-button"
        >
          Previous
        </Button>
        <Button type="button" name="next" className="next action-button">
          Next
        </Button>
      </fieldset>
      <fieldset id="last">
        <h2 className="fs-title">Cargar recompensas</h2>
        <h3 className="fs-subtitle">
          Es obligatorio cargar al menos 1 recompensa
        </h3>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Titulo de la recompensa</Form.Label>
          <Form.Control
            type="text"
            name="tierName"
            placeholder="Titulo"
            onChange={handleChange}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Valor</Form.Label>
          <Form.Control
            type="text"
            name="price"
            placeholder="Valor"
            onChange={handleChange}
          />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicEmail">
          <Form.Label>Descripción breve de la recompensa</Form.Label>
          <Form.Control
            type="text"
            name="description"
            placeholder="Descripción"
            onChange={handleChange}
          />
        </Form.Group>
        <Button
          type="button"
          name="previous"
          className="previous action-button"
        >
          Previous
        </Button>
        <Button type="submit" className="submit action-button">
          Submit
        </Button>
      </fieldset>
    </Form>
  );
};

export default CampaignForm;
