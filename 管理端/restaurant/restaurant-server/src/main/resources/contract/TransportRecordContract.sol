// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

contract TransportRecordContract {
    struct Record {
        uint256 id;
        uint256 productId;
        uint256 userId;
        uint256 transportId;
        string recordTime;
        string lng;
        string lat;
        string locationInfo;
    }

    mapping(uint256 => Record) public records;
    uint256 public lastBlockId;

    event RecordAdded(uint256 indexed id, uint256 indexed productId, uint256 indexed userId);

    function addRecord(
        uint256 productId,
        uint256 userId,
        uint256 transportId,
        string memory recordTime,
        string memory lng,
        string memory lat,
        string memory locationInfo
    ) external {
        lastBlockId++;

        records[lastBlockId] = Record({
        id: lastBlockId,
        productId: productId,
        userId: userId,
        transportId: transportId,
        recordTime: recordTime,
        lng: lng,
        lat: lat,
        locationInfo: locationInfo
        });

        emit RecordAdded(lastBlockId, productId, userId);
    }

    function getRecordsByTransportId(uint256 transportId) external view returns (Record[] memory) {
        Record[] memory result = new Record[](lastBlockId);
        uint256 count = 0;

        for (uint256 i = 1; i <= lastBlockId; i++) {
            if (records[i].transportId == transportId) {
                result[count] = records[i];
                count++;
            }
        }

        assembly {
            mstore(result, count)
        }

        return result;
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