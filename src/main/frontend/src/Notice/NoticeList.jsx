import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import CommonTable from '.././Common/CommonTable';
import CommonTableColumn from '.././Common/CommonTableColumn';
import CommonTableRow from '.././Common/CommonTableRow';
import Pagination from '.././Common/Pagination';
import Header from '.././Common/Header';
import './Notice.css';

const NoticeList = () => {
  const [notice, setNotice] = useState([]);
  const [loading, setLoading] = useState(true);
  const accessToken = sessionStorage.getItem('accessToken');
  const navigate = useNavigate();

  const [currentPage, setCurrentPage] = useState(0);
  const [totalSize, setTotalSize] = useState(0);

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
  };

  useEffect(() => {
    async function getNoticeBoards() {
      try {
        const response = await axios.get('/api/noticeboards', {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
          params: {
            page: currentPage,
            count: 10,
          },
        });
        console.log('데이터 가져오기 성공:', response.data);

        const pageInfo = response.data.pageInfo;
        setTotalSize(pageInfo.totalSize);
        setNotice(response.data.noticeBoards);
        setLoading(false);
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
        setLoading(false);
      }
    }
    getNoticeBoards();
  }, [accessToken, currentPage]);

  return (
    <div className="text-center">
      <Header />
      <CommonTable headersName={['글번호', '제목', '등록일', '관리자', '조회수']}>
        {loading ? (
          <div>게시물을 불러오는 중 입니다 ...</div>
        ) : Array.isArray(notice) && notice.length > 0 ? (
          notice.map((notice, index) => (
            <CommonTableRow key={index}>
              <CommonTableColumn>{notice.id}</CommonTableColumn>
              <CommonTableColumn>
                <Link to={`/api/noticeboards/${notice.id}`}>{notice.title}</Link>
              </CommonTableColumn>
              <CommonTableColumn>{notice.createDate.slice(0, 10)}</CommonTableColumn>
              <CommonTableColumn>{notice.authorLoginId}</CommonTableColumn>
              <CommonTableColumn>{notice.views}</CommonTableColumn>
            </CommonTableRow>
          ))
        ) : (
          <div>게시물이 존재하지 않습니다.</div>
        )}
        <div className="justify-content-center">
          <button className="post-view-go-list-btn" onClick={() => navigate('/api/admins/noticeboards')}>
            글쓰기
          </button>
        </div>
        <Pagination
          currentPage={currentPage}
          lastPage={Math.ceil(totalSize / 10)}
          totalSize={totalSize}
          onPageChange={handlePageChange}
        />
      </CommonTable>
    </div>
  );
};

export default NoticeList;
