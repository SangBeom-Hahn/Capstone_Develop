import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import CommonTable from '.././Component/CommonTable';
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
  const [totalPages, setTotalPages] = useState(1);
  const itemsPerPage = 10;

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
            page: currentPage, // 현재 페이지로 변경
            count: itemsPerPage, // 페이지당 아이템 수로 변경
          },
        });
        console.log('데이터 가져오기 성공:', response.data);
        setNotice(response.data.noticeBoards);
        setTotalPages(response.data.pageInfo.totalPages); // 총 페이지 수 설정
        setLoading(false);
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
        setLoading(false);
      }
    }
    getNoticeBoards();
  }, [accessToken, currentPage]); // currentPage 상태를 의존성 배열에 추가

  return (
    <>
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
        <button className="post-view-go-list-btn" onClick={() => navigate('/api/admins/noticeboards')}>
          글쓰기
        </button>&nbsp;
        <Pagination
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={handlePageChange}
        />
      </CommonTable>
    </>
  );
};

export default NoticeList;
