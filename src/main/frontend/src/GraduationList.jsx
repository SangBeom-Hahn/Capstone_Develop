import React, { useState, useEffect } from 'react';
import Header from './Header';
import { Link } from 'react-router-dom';
import axios from 'axios';

const GraduationList = () => {
  const [graduator, setGraduator] = useState({ content: [], pageable: {} });

  useEffect(() => {
    axios.get('/api/graduation/graduate_management')
      .then(response => {
        setGraduator(response.data);
      })
      .catch(error => {
        console.error('데이터 가져오기 실패:', error);
      });
  }, []);

  const startBlockPage = 1;
  const endBlockPage = 10;

  return (
  <>
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
                    <h1 className="h2 text-white mb-2">졸업자조회</h1>
                    <div>&nbsp;</div>
                  </div>
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
                    <table id="table" className="table mb-0" border-color="black">
                      <thead className="table-dark">
                        <tr style={{ textAlign: 'center' }}>
                          <th data-field="no">
                            <div className="th-inner sortable both">No.</div>
                          </th>
                          <th data-field="studentId">
                            <div className="th-inner sortable both">학번</div>
                          </th>
                          <th data-field="studentName">
                            <div className="th-inner sortable both">학생 이름</div>
                          </th>
                          <th data-field="professorName">
                            <div className="th-inner sortable both">교수 이름</div>
                          </th>
                          <th data-field="graduationDate">
                            <div className="th-inner sortable both">학생 졸업날짜</div>
                          </th>
                          <th data-field="step">
                            <div className="th-inner sortable both">단계</div>
                          </th>
                          <th data-field="state">
                            <div className="th-inner sortable both">상태</div>
                          </th>
                          <th data-field="otherQualifications">
                            <div className="th-inner sortable both">기타 자격</div>
                          </th>
                          <th data-field="capstoneCompletion">
                            <div className="th-inner sortable both">캡스톤 이수</div>
                          </th>
                        </tr>
                      </thead>
                      {graduator.content.map((data, index) => (
                        <tbody key={data.id}>
                          <tr style={{ textAlign: 'center' }}>
                            <td>{index + 1}</td>
                            <td>{data.studentId}</td>
                            <td><a href={`/api/userStatus/approvalUser/${data.studentId}`}>{data.studentName}</a></td>
                            <td>{data.professorName}</td>
                            <td>{data.graduationDate}</td>
                            <td>{data.step}</td>
                            <td>{data.state}</td>
                            <td>{data.otherQualifications}</td>
                            <td>{data.capstoneCompletion}</td>
                          </tr>
                        </tbody>
                      ))}
                      </table>
                    </div>
                  </div>
                  {/* 페이징 영역 */}
                  <div className="text-xs-center">
                    <ul className="pagination justify-content-center">
                      {/* 이전 */}
                      {graduator.first ? null : (
                        <React.Fragment>
                          <li className="page-item">
                            <Link to={`/api/graduation/graduate_management?page=0&size=10`} className="page-link">
                              처음
                            </Link>
                          </li>
                          <li className="page-item">
                            <Link to={`/api/graduation/graduate_management?page=${graduator.number - 1}&size=10`} className="page-link">
                              이전
                            </Link>
                          </li>
                        </React.Fragment>
                      )}
                      {Array.from({ length: endBlockPage - startBlockPage + 1 }).map((_, i) => {
                        const pageNumber = startBlockPage + i ;
                        return (
                          <li key={pageNumber} className={`page-item ${graduator.pageable.pageNumber + 1 === pageNumber ? 'disabled' : ''}`}>
                            <Link to={`/api/graduation/graduate_management?page=${pageNumber}&size=10`} className="page-link">
                              {pageNumber}
                            </Link>
                          </li>
                        );
                      })}
                      {/* 다음 */}
                      {graduator.last ? null : (
                        <React.Fragment>
                          <li className="page-item">
                            <Link to={`/api/graduation/graduate_management?page=${graduator.number + 1}&size=10`} className="page-link">
                              다음
                            </Link>
                          </li>
                          <li className="page-item">
                            <Link to={`/api/graduation/graduate_management?page=${graduator.totalPages - 1}&size=10`} className="page-link">
                              마지막
                            </Link>
                          </li>
                        </React.Fragment>
                      )}
                    </ul>
                  </div>
                  {/* 마지막 */}
                </div>
                <div className="d-flex justify-content-end">
                  <form action="api/graduation/graduate_management.download" method="get">
                    <button className="btn btn-primary text-white float-right">다운로드</button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default GraduationList;
