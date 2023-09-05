import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import CommonTable from '.././Component/CommonTable';
import CommonTableColumn from '.././Component/CommonTableColumn';
import CommonTableRow from '.././Component/CommonTableRow';
import Header from '.././Header';
import './Notice.css';

const NoticeList = () => {
  const [notice, setNotice] = useState([]);
  const [loading, setLoading] = useState(true); // 로딩 상태 추가
  const accessToken = sessionStorage.getItem('accessToken');

  useEffect(() => {
    async function getNoticeBoards() {
      try {
        const response = await axios.get('/api/noticeboards', {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
          params: {
            page: 1,
            count: 3,
          },
        });
        console.log('데이터 가져오기 성공:', response.data);
        setNotice(response.data.noticeBoards);
        setLoading(false); // 데이터 로딩 완료 후 로딩 상태 변경
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
        setLoading(false); // 데이터 로딩 실패 시에도 로딩 상태 변경
      }
    }
    getNoticeBoards();
  }, [accessToken]);

  return (
    <>
      <Header />
      <CommonTable headersName={['글번호', '제목', '등록일', '관리자', '조회수']}>
        {loading ? ( // 로딩 중일 때
          <div>게시물을 불러오는 중 입니다 ...</div>
        ) : Array.isArray(notice) && notice.length > 0 ? ( // 게시물이 있을 때
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
        ) : ( // 게시물이 없을 때
          <div>게시물이 존재하지 않습니다.</div>
        )}
      </CommonTable>
    </>
  );
};

export default NoticeList;
