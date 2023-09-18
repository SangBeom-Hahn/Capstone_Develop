import React from 'react';

const CommonTableColumn = ({ children }) => {
  return (
    <td className="common-table-row">
      {
        children
      }
    </td>
  )
}

export default CommonTableColumn;
