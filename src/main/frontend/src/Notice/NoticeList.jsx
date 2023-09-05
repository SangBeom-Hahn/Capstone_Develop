import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import CommonTable from '.././Component/CommonTable';
import CommonTableColumn from '.././Component/CommonTableColumn';
import CommonTableRow from '.././Component/CommonTableRow';
import Header from '.././Header';

const NoticeList = () => {
  const [notice, setNotice] = useState([]);
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
      } catch (error) {
        console.error('데이터 가져오기 오류:', error);
      }
    }
    getNoticeBoards();
  }, [accessToken]);

  return (
    <>
      <Header />
      <CommonTable headersName={['글번호', '제목', '등록일', '관리자', '조회수']}>
        {Array.isArray(notice) && notice.length > 0 ? (
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
          <div>게시물을 불러오는 중 입니다 ...</div>
        )}
      </CommonTable>
    </>
  );
};

export default NoticeList;
