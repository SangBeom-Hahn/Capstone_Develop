import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import Header from './Common/Header';

const ModifyGuide = ({ onSubmit, hasFieldErrors, getFieldError }) => {
  const [data, setData] = useState({ text: ''});
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('/api/graduation/guide')
      .then(response => {
        setData({ text: response.data.text });
        console.log("데이터 가져오기 성공");
      })
      .catch(error => {
        console.error('데이터 가져오기 실패', error);
      });
    },[]);

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    if (window.confirm('수정하시겠습니까?')) {
      try {
        const response = await axios.post('/api/graduation/modifyGuide', {
          text: data.text,
        });
        console.log('글 수정 요청 성공');
        navigate('/api/graduation/guide');
      } catch (error) {
          console.error('글 수정 요청 실패');
          navigate('/api/graduation/guide');
        }
    }
  };
  return (
    <div>
      <Header />
      <div className="bg-shape bg-secondary">
        <div className="container">
          <div className="row">
            <div className="offset-xl-1 col-xl-10 col-lg-12 col-md-12 col-12">
              <div className="pt-lg-18 pb-lg-16 py-12">
                <div className="row align-items-center">
                  <div className="col-lg-12 col-md-12 col-12 mb-3">
                    <div className="custom-breadcrumb"></div>
                  </div>
                  <div className="col-lg-6 col-md-6 col-12">
                    <h1 className="h2 text-white mb-2">안내 및 내규</h1>
                    <p className="text-white-50 lead">글 수정하기</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="pb-10 mt-n10">
        <div className="container">
          <div className="row">
            <div className="offset-lg-1 col-lg-10 col-md-12 col-12">
              <div className="card">
                <div className="card-body p-4 p-lg-7">
                  <form id="myForm" onSubmit={handleFormSubmit}>
                    <div className="form-group">
                      <textarea id="editor" name="text" value={data.text} onChange={(e) => setData({ text: e.target.value })} style= {{ height: '400px', width: '100%'}} />
                      <div className="d-flex justify-content-end">
                        <button type="submit" className="btn btn-primary text-white float-right">
                          쓰기
                       </button>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ModifyGuide;
