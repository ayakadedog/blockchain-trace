// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

contract TaskRecordContract {
    struct Record {
        uint256 id;
        uint256 taskId;
        uint256 productId;
        uint256 userId;
        uint256 companyId;
        string recordTime;
        string remarks;
        string taskName;
        string content;
    }

    mapping(uint256 => Record) public records;
    uint256 public lastBlockId;

    event RecordAdded(uint256 indexed id, uint256 indexed taskId, uint256 indexed productId);

    function addRecord(
        uint256 taskId,
        uint256 productId,
        uint256 userId,
        uint256 companyId,
        string memory recordTime,
        string memory remarks,
        string memory taskName,
        string memory content
    ) external {
        lastBlockId++;

        records[lastBlockId] = Record({
        id: lastBlockId,
        taskId: taskId,
        productId: productId,
        userId: userId,
        companyId: companyId,
        recordTime: recordTime,
        remarks: remarks,
        taskName: taskName,
        content: content
        });

        emit RecordAdded(lastBlockId, taskId, productId);
    }

    function getRecordsByProductId(uint256 productId) external view returns (Record[] memory) {
        Record[] memory result = new Record[](lastBlockId);
        uint256 count = 0;

        for (uint256 i = 1; i <= lastBlockId; i++) {
            if (records[i].productId == productId) {
                result[count] = records[i];
                count++;
            }
        }

        assembly {
            mstore(result, count)
        }

        return result;
    }

    function getRecordsByTaskId(uint256 taskId) external view returns (Record[] memory) {
        Record[] memory result = new Record[](lastBlockId);
        uint256 count = 0;

        for (uint256 i = 1; i <= lastBlockId; i++) {
            if (records[i].taskId == taskId) {
                result[count] = records[i];
                count++;
            }
        }

        assembly {
            mstore(result, count)
        }

        return result;
    }

}
