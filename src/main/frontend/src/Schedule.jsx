import React, { useState } from 'react';
import Header from './Common/Header'; // Header 컴포넌트를 import

const Schedule = () => {
  return (
    <div>
      <Header />

      {/* 배경 */}
      <div className="bg-shape bg-secondary">
        <div className="container">
          <div className="row">
            <div className="offset-xl-1 col-xl-10 col-lg-12 col-md-12 col-12">
              <div className="pt-lg-18 pb-lg-16 py-12 ">
                <div className="row align-items-center">
                 <div>&nbsp;</div>
                  <div className="col-lg-6 col-md-6 col-12">
                    <h1 className="h2 text-white mb-2">진행일정</h1>
                    <div>&nbsp;</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* 내용 */}
      <div className="pb-10 mt-n10">
        <div className="container">
          <div className="row">
            <div className="offset-lg-1 col-lg-10 col-md-12 col-12">
              <div className="card">
                <div className="card-body p-4 p-lg-7">
                  <div>
                    <div className="card table-responsive">
                      <table id="table" className="table mb-0" style={{ borderColor: 'black' }}>
                        <thead className="table-dark">
                          <tr style={{ textAlign: 'center' }}>
                            <th data-field="step">
                              <div className="th-inner sortable both">단계</div>
                            </th>
                            <th data-field="start_date">
                              <div className="th-inner sortable both">시작 일정</div>
                            </th>
                            <th data-field="final_date">
                              <div className="th-inner sortable both">종료 일정</div>
                            </th>
                            <th data-field="state">
                              <div className="th-inner sortable both">상태</div>
                            </th>
                          </tr>
                        </thead>
                        <tbody>
                          {/*}{schedules.map((data, index) => (
                            <tr key={index} style={{ textAlign: 'center' }}>
                              <td>{data.step.stepToString}</td>
                              <td>{data.startDate}</td>
                              <td>{data.endDate}</td>
                              <td>{data.scheduleState.scheduleStateToString}</td>
                            </tr>
                          ))}*/}
                        </tbody>
                      </table>
                    </div>
                  </div>
                  {/* 추가 */}
                </div>
              </div>
              {/* 추가 */}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Schedule;
