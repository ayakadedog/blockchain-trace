// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

contract ProductRecord {
    struct Record {
        uint256 blockId;
        uint256 productId;
        uint256 userId;
        string temperature;
        string illumination;
        string remark;
        string pictureIpfsHash;
        string water;
        string growthSituation;
        string recordTime;
    }

    mapping(uint256 => Record) public records;
    uint256 public lastBlockId;

    event RecordAdded(uint256 indexed blockId, uint256 indexed productId, uint256 indexed userId);

    function addRecord(
        uint256 productId,
        uint256 userId,
        string memory temperature,
        string memory illumination,
        string memory remark,
        string memory pictureIpfsHash,
        string memory water,
        string memory growthSituation,
        string memory recordTime
    ) external {
        lastBlockId++;

        records[lastBlockId] = Record({
        blockId: lastBlockId,
        productId: productId,
        userId: userId,
        temperature: temperature,
        illumination: illumination,
        remark: remark,
        pictureIpfsHash: pictureIpfsHash,
        water: water,
        growthSituation: growthSituation,
        recordTime: recordTime
        });

        emit RecordAdded(lastBlockId, productId, userId);
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
}