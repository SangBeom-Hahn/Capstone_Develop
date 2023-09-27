import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Header from './Common/Header';

const Guide = ({ userId }) => {
  const [data, setData] = useState({ text: '', id: '' });

  useEffect(() => {
    axios.get('/api/graduation/guide')
      .then(response => {
        setData(response.data);
      })
      .catch(error => {
        console.error('데이터 가져오기 실패:', error);
      });
  }, []);

  return (
    <div>
      <Header />
      <div className="bg-shape bg-secondary">
        <div className="container">
          <div className="row">
            <div className="offset-xl-1 col-xl-10 col-lg-12 col-md-12 col-12">
              <div className="pt-lg-18 pb-lg-16 py-12">
                <div className="row align-items-center">
                 <div>&nbsp;</div>
                  <div className="col-lg-6 col-md-6 col-12">
                    <h1 className="h2 text-white mb-2">안내 및 내규</h1>
                    <div>&nbsp;</div>
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
                  <div>
                    <div className="card table-responsive">
                      <div className="card-body">
                        <div className="row mt-2">
                          <div className="col-12">
                            <div className="card-text" style={{ height: '400px' }}>
                              {data.text}
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="d-flex justify-content-end">
                    {userId && userId.includes('admin') && (
                      <Link to={`modifyGuide/${data.id}`} className="btn btn-primary text-white float-right">
                        수정
                      </Link>
                    )}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Guide;
