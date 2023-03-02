const Comments = ({ content }) => {
  return (
    <div className="campaign-comments">
      <h4>Deja tu comentario:</h4>
      <div className="comments-container m-auto" style={{ width: "90%" }}>
        {content.length > 0 ? (
          content.map((c, i) => {
            return (
              <div className="comment-box" key={i}>
                <div className="comment-box-header d-flex">
                  <img
                    src="https://dummyimage.com/25x25/a3a3a3/fff.png"
                    alt="profile-picture"
                  />{" "}
                  &nbsp;
                  <h5>
                    {c.user.firstName} {c.user.lastName}
                  </h5>
                </div>
                <div className="comment-content">
                  <p className="comment-content-text">{c.description}</p>
                  <div className="comment-content-data d-flex justify-content-between">
                    <span>{c.creationDate}</span>
                    <a href="#">Denunciar Mensaje</a>
                  </div>
                </div>
              </div>
            );
          })
        ) : (
          <h5>No hay comentarios para mostrar</h5>
        )}
      </div>
    </div>
  );
};

export default Comments;
