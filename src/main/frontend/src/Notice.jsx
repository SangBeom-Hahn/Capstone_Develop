import React from 'react';
import Header from './Header'; // Header 컴포넌트를 import

const Notice = () => {
  return (
    <div>
      <Header />
      <div className="bg-shape bg-secondary">
        <div className="container">
          <div className="row">
            <div className="offset-xl-1 col-xl-10 col-lg-12 col-md-12 col-12">
              <div className="pt-lg-18 pb-lg-16 py-12 ">
                <div className="row align-items-center">
                  <div>&nbsp;</div>
                    <div className="col-lg-6 col-md-6 col-12">
                      <h1 className="h2 text-white mb-2">공지사항</h1>
                       <div>&nbsp;</div>
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

export default Notice;
