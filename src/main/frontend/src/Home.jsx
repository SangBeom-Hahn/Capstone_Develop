import { useNavigate } from 'react-router-dom';

const Home = () => {
  const navigate = useNavigate();

  const handleHomeClick = () => {
       navigate('/api/home');
     };
  const handleLoginClick = () => {
     navigate('/api/Login');
   };
  const handleNoticeClick = () => {
    navigate('/api/notice');
  };
  const handleKutisClick = () => {
      window.location.href = 'https://kutis.kyonggi.ac.kr/webkutis/view/indexWeb.jsp';
   };
  const handleLMSClick = () => {
    window.location.href = 'https://lms.kyonggi.ac.kr/login.php';
  };
  const handleKGUClick = () => {
      window.location.href = 'https://www.kyonggi.ac.kr/www/index.do';
   };
   const handleAIHOMEClick = () => {
       window.location.href = 'http://cs.kyonggi.ac.kr:8080/Index';
   };
   const handleSWUNIVClick = () => {
       window.location.href = 'https://swuniv.kyonggi.ac.kr/';
    };

  return (
    <>
      {/* header */}
      <div className="header position-sticky border-3 border-top border-primary bg-dark">
        {/* navigation start */}
        <div className="container">
          <nav className="navbar navbar-expand-lg navbar-default">
            <a className="navbar-brand" onClick={handleHomeClick}>
              <img src="/img/cspop_logo.png" width="110" height="40" alt="" />
            </a>
            <ul className="navbar-nav ms-auto me-lg-3">
            </ul>
            <div className="header-btn">
              <button onClick={handleLoginClick} className="btn btn-primary">
                Login
              </button>
            </div>
          </nav>
        </div>
        {/* navigation close */}
      </div>

      {/* pageheader section */}
      <div className="bg-shape bg-dark">
        <div className="container">
          <div className="row">
            <div className="col-xl-12 col-lg-12 col-md-12 col-12"></div>
          </div>
        </div>
      </div>

      {/* dashboard nav */}
      <div className="py-12">
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-lg-8 col-md-12 col-12">
              &nbsp;
              <h1 className="fw-bold mb-3">경기대학교 졸업 시스템</h1>
              <p className="mb-4">
                당신의 졸업 요건을 확인해 보세요.
              </p>
              <button className="btn btn-success" onClick={handleNoticeClick}>
                Graduation Start
              </button>
            </div>
          </div>
        </div>
      </div>

      {/* footer */}
      <div className="footer pt-11 pb-3 bg-dark text-body">
        <div className="container">
          <div className="row justify-content-center">
            <div className="col-lg-2 col-md-4 col-6">
              <div className="mb-4" onClick={handleKutisClick}>
                <h4 className="mb-4 text-white">Kutis</h4>
              </div>
            </div>
            <div className="col-lg-2 col-md-4 col-6">
              <div className="mb-4" onClick={handleLMSClick}>
                <h4 className="mb-4 text-white">LMS</h4>
              </div>
            </div>
            <div className="col-lg-2 col-md-4 col-6">
              <div className="mb-4" onClick={handleKGUClick}>
                <h4 className="mb-4 text-white">KGU</h4>
              </div>
            </div>
            <div className="col-lg-2 col-md-4 col-6">
              <div className="mb-4" onClick={handleAIHOMEClick}>
                <h4 className="mb-4 text-white">AI-HOME</h4>
              </div>
            </div>
            <div className="col-lg-2 col-md-4 col-6">
              <div className="mb-4" onClick={handleSWUNIVClick}>
                <h4 className="mb-4 text-white">SW-UNIV</h4>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;
