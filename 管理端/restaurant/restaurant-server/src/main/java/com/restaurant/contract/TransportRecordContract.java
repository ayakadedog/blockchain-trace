package com.restaurant.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicArray;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple7;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple8;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class TransportRecordContract extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50610fdc806100206000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c8063344610671461005c57806343c1118a1461008c578063b8471ea2146100a1578063c2a4250b146100c1578063ecfe570a146100d8575b600080fd5b61006f61006a366004610bf9565b6100eb565b604051610083989796959493929190610c5f565b60405180910390f35b61009f61009a366004610d79565b61034e565b005b6100b46100af366004610bf9565b610476565b6040516100839190610e43565b6100ca60015481565b604051908152602001610083565b6100b46100e6366004610bf9565b6107cd565b60006020819052908152604090208054600182015460028301546003840154600485018054949593949293919261012190610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461014d90610f2c565b801561019a5780601f1061016f5761010080835404028352916020019161019a565b820191906000526020600020905b81548152906001019060200180831161017d57829003601f168201915b5050505050908060050180546101af90610f2c565b80601f01602080910402602001604051908101604052809291908181526020018280546101db90610f2c565b80156102285780601f106101fd57610100808354040283529160200191610228565b820191906000526020600020905b81548152906001019060200180831161020b57829003601f168201915b50505050509080600601805461023d90610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461026990610f2c565b80156102b65780601f1061028b576101008083540402835291602001916102b6565b820191906000526020600020905b81548152906001019060200180831161029957829003601f168201915b5050505050908060070180546102cb90610f2c565b80601f01602080910402602001604051908101604052809291908181526020018280546102f790610f2c565b80156103445780601f1061031957610100808354040283529160200191610344565b820191906000526020600020905b81548152906001019060200180831161032757829003601f168201915b5050505050905088565b6001805490600061035e83610f67565b909155505060408051610100810182526001805480835260208084018c81528486018c8152606086018c8152608087018c815260a088018c905260c088018b905260e088018a905260009586528585529790942086518155915194820194909455925160028401559051600383015592518051929391926103e59260048501920190610b1b565b5060a08201518051610401916005840191602090910190610b1b565b5060c0820151805161041d916006840191602090910190610b1b565b5060e08201518051610439916007840191602090910190610b1b565b50506001546040518892508991907facae88f8c5ced2bba1920b1be99fa426c3fff4cd1d557ce6665f281b096c3e2290600090a450505050505050565b6060600060015467ffffffffffffffff81111561049557610495610cd6565b6040519080825280602002602001820160405280156104ce57816020015b6104bb610b9f565b8152602001906001900390816104b35790505b509050600060015b60015481116107c4576000818152602081905260409020600301548514156107b257600080828152602001908152602001600020604051806101000160405290816000820154815260200160018201548152602001600282015481526020016003820154815260200160048201805461054e90610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461057a90610f2c565b80156105c75780601f1061059c576101008083540402835291602001916105c7565b820191906000526020600020905b8154815290600101906020018083116105aa57829003601f168201915b505050505081526020016005820180546105e090610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461060c90610f2c565b80156106595780601f1061062e57610100808354040283529160200191610659565b820191906000526020600020905b81548152906001019060200180831161063c57829003601f168201915b5050505050815260200160068201805461067290610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461069e90610f2c565b80156106eb5780601f106106c0576101008083540402835291602001916106eb565b820191906000526020600020905b8154815290600101906020018083116106ce57829003601f168201915b5050505050815260200160078201805461070490610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461073090610f2c565b801561077d5780601f106107525761010080835404028352916020019161077d565b820191906000526020600020905b81548152906001019060200180831161076057829003601f168201915b50505050508152505083838151811061079857610798610f90565b602002602001018190525081806107ae90610f67565b9250505b806107bc81610f67565b9150506104d6565b50815292915050565b6060600060015467ffffffffffffffff8111156107ec576107ec610cd6565b60405190808252806020026020018201604052801561082557816020015b610812610b9f565b81526020019060019003908161080a5790505b509050600060015b60015481116107c457600081815260208190526040902060010154851415610b095760008082815260200190815260200160002060405180610100016040529081600082015481526020016001820154815260200160028201548152602001600382015481526020016004820180546108a590610f2c565b80601f01602080910402602001604051908101604052809291908181526020018280546108d190610f2c565b801561091e5780601f106108f35761010080835404028352916020019161091e565b820191906000526020600020905b81548152906001019060200180831161090157829003601f168201915b5050505050815260200160058201805461093790610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461096390610f2c565b80156109b05780601f10610985576101008083540402835291602001916109b0565b820191906000526020600020905b81548152906001019060200180831161099357829003601f168201915b505050505081526020016006820180546109c990610f2c565b80601f01602080910402602001604051908101604052809291908181526020018280546109f590610f2c565b8015610a425780601f10610a1757610100808354040283529160200191610a42565b820191906000526020600020905b815481529060010190602001808311610a2557829003601f168201915b50505050508152602001600782018054610a5b90610f2c565b80601f0160208091040260200160405190810160405280929190818152602001828054610a8790610f2c565b8015610ad45780601f10610aa957610100808354040283529160200191610ad4565b820191906000526020600020905b815481529060010190602001808311610ab757829003601f168201915b505050505081525050838381518110610aef57610aef610f90565b60200260200101819052508180610b0590610f67565b9250505b80610b1381610f67565b91505061082d565b828054610b2790610f2c565b90600052602060002090601f016020900481019282610b495760008555610b8f565b82601f10610b6257805160ff1916838001178555610b8f565b82800160010185558215610b8f579182015b82811115610b8f578251825591602001919060010190610b74565b50610b9b929150610be4565b5090565b60405180610100016040528060008152602001600081526020016000815260200160008152602001606081526020016060815260200160608152602001606081525090565b5b80821115610b9b5760008155600101610be5565b600060208284031215610c0b57600080fd5b5035919050565b6000815180845260005b81811015610c3857602081850181015186830182015201610c1c565b81811115610c4a576000602083870101525b50601f01601f19169290920160200192915050565b60006101008a8352896020840152886040840152876060840152806080840152610c8b81840188610c12565b905082810360a0840152610c9f8187610c12565b905082810360c0840152610cb38186610c12565b905082810360e0840152610cc78185610c12565b9b9a5050505050505050505050565b634e487b7160e01b600052604160045260246000fd5b600082601f830112610cfd57600080fd5b813567ffffffffffffffff80821115610d1857610d18610cd6565b604051601f8301601f19908116603f01168101908282118183101715610d4057610d40610cd6565b81604052838152866020858801011115610d5957600080fd5b836020870160208301376000602085830101528094505050505092915050565b600080600080600080600060e0888a031215610d9457600080fd5b873596506020880135955060408801359450606088013567ffffffffffffffff80821115610dc157600080fd5b610dcd8b838c01610cec565b955060808a0135915080821115610de357600080fd5b610def8b838c01610cec565b945060a08a0135915080821115610e0557600080fd5b610e118b838c01610cec565b935060c08a0135915080821115610e2757600080fd5b50610e348a828b01610cec565b91505092959891949750929550565b60006020808301818452808551808352604092508286019150828160051b87010184880160005b83811015610f1e57603f198984030185528151610100815185528882015189860152878201518886015260608083015181870152506080808301518282880152610eb683880182610c12565b9250505060a08083015186830382880152610ed18382610c12565b9250505060c08083015186830382880152610eec8382610c12565b9250505060e08083015192508582038187015250610f0a8183610c12565b968901969450505090860190600101610e6a565b509098975050505050505050565b600181811c90821680610f4057607f821691505b60208210811415610f6157634e487b7160e01b600052602260045260246000fd5b50919050565b6000600019821415610f8957634e487b7160e01b600052601160045260246000fd5b5060010190565b634e487b7160e01b600052603260045260246000fdfea264697066735822122029ed0ea1d093bed32be30e4e8070effa8d5b11248aa80d05384abd454aea510264736f6c634300080b0033"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50610fdc806100206000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c80632337ea581461005c578063681ebc9f14610085578063a535c5621461009c578063c860f85e146100af578063f07d68b1146100d6575b600080fd5b61006f61006a366004610bf9565b6100eb565b60405161007c9190610c5f565b60405180910390f35b61008e60015481565b60405190815260200161007c565b61006f6100aa366004610bf9565b610442565b6100c26100bd366004610bf9565b610790565b60405161007c989796959493929190610d48565b6100e96100e4366004610e62565b6109f3565b005b6060600060015467ffffffffffffffff81111561010a5761010a610dbf565b60405190808252806020026020018201604052801561014357816020015b610130610b1b565b8152602001906001900390816101285790505b509050600060015b6001548111610439576000818152602081905260409020600301548514156104275760008082815260200190815260200160002060405180610100016040529081600082015481526020016001820154815260200160028201548152602001600382015481526020016004820180546101c390610f2c565b80601f01602080910402602001604051908101604052809291908181526020018280546101ef90610f2c565b801561023c5780601f106102115761010080835404028352916020019161023c565b820191906000526020600020905b81548152906001019060200180831161021f57829003601f168201915b5050505050815260200160058201805461025590610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461028190610f2c565b80156102ce5780601f106102a3576101008083540402835291602001916102ce565b820191906000526020600020905b8154815290600101906020018083116102b157829003601f168201915b505050505081526020016006820180546102e790610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461031390610f2c565b80156103605780601f1061033557610100808354040283529160200191610360565b820191906000526020600020905b81548152906001019060200180831161034357829003601f168201915b5050505050815260200160078201805461037990610f2c565b80601f01602080910402602001604051908101604052809291908181526020018280546103a590610f2c565b80156103f25780601f106103c7576101008083540402835291602001916103f2565b820191906000526020600020905b8154815290600101906020018083116103d557829003601f168201915b50505050508152505083838151811061040d5761040d610f67565b6020026020010181905250818061042390610f7d565b9250505b8061043181610f7d565b91505061014b565b50815292915050565b6060600060015467ffffffffffffffff81111561046157610461610dbf565b60405190808252806020026020018201604052801561049a57816020015b610487610b1b565b81526020019060019003908161047f5790505b509050600060015b60015481116104395760008181526020819052604090206001015485141561077e57600080828152602001908152602001600020604051806101000160405290816000820154815260200160018201548152602001600282015481526020016003820154815260200160048201805461051a90610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461054690610f2c565b80156105935780601f1061056857610100808354040283529160200191610593565b820191906000526020600020905b81548152906001019060200180831161057657829003601f168201915b505050505081526020016005820180546105ac90610f2c565b80601f01602080910402602001604051908101604052809291908181526020018280546105d890610f2c565b80156106255780601f106105fa57610100808354040283529160200191610625565b820191906000526020600020905b81548152906001019060200180831161060857829003601f168201915b5050505050815260200160068201805461063e90610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461066a90610f2c565b80156106b75780601f1061068c576101008083540402835291602001916106b7565b820191906000526020600020905b81548152906001019060200180831161069a57829003601f168201915b505050505081526020016007820180546106d090610f2c565b80601f01602080910402602001604051908101604052809291908181526020018280546106fc90610f2c565b80156107495780601f1061071e57610100808354040283529160200191610749565b820191906000526020600020905b81548152906001019060200180831161072c57829003601f168201915b50505050508152505083838151811061076457610764610f67565b6020026020010181905250818061077a90610f7d565b9250505b8061078881610f7d565b9150506104a2565b6000602081905290815260409020805460018201546002830154600384015460048501805494959394929391926107c690610f2c565b80601f01602080910402602001604051908101604052809291908181526020018280546107f290610f2c565b801561083f5780601f106108145761010080835404028352916020019161083f565b820191906000526020600020905b81548152906001019060200180831161082257829003601f168201915b50505050509080600501805461085490610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461088090610f2c565b80156108cd5780601f106108a2576101008083540402835291602001916108cd565b820191906000526020600020905b8154815290600101906020018083116108b057829003601f168201915b5050505050908060060180546108e290610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461090e90610f2c565b801561095b5780601f106109305761010080835404028352916020019161095b565b820191906000526020600020905b81548152906001019060200180831161093e57829003601f168201915b50505050509080600701805461097090610f2c565b80601f016020809104026020016040519081016040528092919081815260200182805461099c90610f2c565b80156109e95780601f106109be576101008083540402835291602001916109e9565b820191906000526020600020905b8154815290600101906020018083116109cc57829003601f168201915b5050505050905088565b60018054906000610a0383610f7d565b909155505060408051610100810182526001805480835260208084018c81528486018c8152606086018c8152608087018c815260a088018c905260c088018b905260e088018a90526000958652858552979094208651815591519482019490945592516002840155905160038301559251805192939192610a8a9260048501920190610b60565b5060a08201518051610aa6916005840191602090910190610b60565b5060c08201518051610ac2916006840191602090910190610b60565b5060e08201518051610ade916007840191602090910190610b60565b50506001546040518892508991907f8ab2a636d95896cdfa3b100b15c58fa15a1f34a692c5a5875ca3a9d836e0e9e590600090a450505050505050565b60405180610100016040528060008152602001600081526020016000815260200160008152602001606081526020016060815260200160608152602001606081525090565b828054610b6c90610f2c565b90600052602060002090601f016020900481019282610b8e5760008555610bd4565b82601f10610ba757805160ff1916838001178555610bd4565b82800160010185558215610bd4579182015b82811115610bd4578251825591602001919060010190610bb9565b50610be0929150610be4565b5090565b5b80821115610be05760008155600101610be5565b600060208284031215610c0b57600080fd5b5035919050565b6000815180845260005b81811015610c3857602081850181015186830182015201610c1c565b81811115610c4a576000602083870101525b50601f01601f19169290920160200192915050565b60006020808301818452808551808352604092508286019150828160051b87010184880160005b83811015610d3a57603f198984030185528151610100815185528882015189860152878201518886015260608083015181870152506080808301518282880152610cd283880182610c12565b9250505060a08083015186830382880152610ced8382610c12565b9250505060c08083015186830382880152610d088382610c12565b9250505060e08083015192508582038187015250610d268183610c12565b968901969450505090860190600101610c86565b509098975050505050505050565b60006101008a8352896020840152886040840152876060840152806080840152610d7481840188610c12565b905082810360a0840152610d888187610c12565b905082810360c0840152610d9c8186610c12565b905082810360e0840152610db08185610c12565b9b9a5050505050505050505050565b63b95aa35560e01b600052604160045260246000fd5b600082601f830112610de657600080fd5b813567ffffffffffffffff80821115610e0157610e01610dbf565b604051601f8301601f19908116603f01168101908282118183101715610e2957610e29610dbf565b81604052838152866020858801011115610e4257600080fd5b836020870160208301376000602085830101528094505050505092915050565b600080600080600080600060e0888a031215610e7d57600080fd5b873596506020880135955060408801359450606088013567ffffffffffffffff80821115610eaa57600080fd5b610eb68b838c01610dd5565b955060808a0135915080821115610ecc57600080fd5b610ed88b838c01610dd5565b945060a08a0135915080821115610eee57600080fd5b610efa8b838c01610dd5565b935060c08a0135915080821115610f1057600080fd5b50610f1d8a828b01610dd5565b91505092959891949750929550565b600181811c90821680610f4057607f821691505b60208210811415610f615763b95aa35560e01b600052602260045260246000fd5b50919050565b63b95aa35560e01b600052603260045260246000fd5b6000600019821415610f9f5763b95aa35560e01b600052601160045260246000fd5b506001019056fea2646970667358221220c8e030957f1efb8e240fe4aadfdfea7d58ef5b244875c0fd7afa1d336924ab9564736f6c634300080b0033"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"userId\",\"type\":\"uint256\"}],\"name\":\"RecordAdded\",\"type\":\"event\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":4,\"value\":[1]}],\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"userId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"transportId\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"recordTime\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"lng\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"lat\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"locationInfo\",\"type\":\"string\"}],\"name\":\"addRecord\",\"outputs\":[],\"selector\":[1136726410,4034750641],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":4,\"value\":[1]}],\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"}],\"name\":\"getRecordsByProductId\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"userId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"transportId\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"recordTime\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"lng\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"lat\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"locationInfo\",\"type\":\"string\"}],\"internalType\":\"struct TransportRecordContract.Record[]\",\"name\":\"\",\"type\":\"tuple[]\"}],\"selector\":[3976091402,2771764578],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":4,\"value\":[1]}],\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"transportId\",\"type\":\"uint256\"}],\"name\":\"getRecordsByTransportId\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"userId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"transportId\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"recordTime\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"lng\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"lat\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"locationInfo\",\"type\":\"string\"}],\"internalType\":\"struct TransportRecordContract.Record[]\",\"name\":\"\",\"type\":\"tuple[]\"}],\"selector\":[3091668642,590867032],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":4,\"value\":[1]}],\"inputs\":[],\"name\":\"lastBlockId\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"selector\":[3265537291,1746844831],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":3,\"slot\":0,\"value\":[0]}],\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"records\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"userId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"transportId\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"recordTime\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"lng\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"lat\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"locationInfo\",\"type\":\"string\"}],\"selector\":[877006951,3361798238],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ADDRECORD = "addRecord";

    public static final String FUNC_GETRECORDSBYPRODUCTID = "getRecordsByProductId";

    public static final String FUNC_GETRECORDSBYTRANSPORTID = "getRecordsByTransportId";

    public static final String FUNC_LASTBLOCKID = "lastBlockId";

    public static final String FUNC_RECORDS = "records";

    public static final Event RECORDADDED_EVENT = new Event("RecordAdded",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}));
    ;

    protected TransportRecordContract(String contractAddress, Client client,
                                      CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public List<RecordAddedEventResponse> getRecordAddedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RECORDADDED_EVENT, transactionReceipt);
        ArrayList<RecordAddedEventResponse> responses = new ArrayList<RecordAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RecordAddedEventResponse typedResponse = new RecordAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.productId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.userId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public TransactionReceipt addRecord(BigInteger productId, BigInteger userId,
                                        BigInteger transportId, String recordTime, String lng, String lat,
                                        String locationInfo) {
        final Function function = new Function(
                FUNC_ADDRECORD,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(productId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(userId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(transportId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(recordTime),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(lng),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(lat),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(locationInfo)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForAddRecord(BigInteger productId, BigInteger userId,
                                                   BigInteger transportId, String recordTime, String lng, String lat,
                                                   String locationInfo) {
        final Function function = new Function(
                FUNC_ADDRECORD,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(productId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(userId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(transportId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(recordTime),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(lng),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(lat),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(locationInfo)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String addRecord(BigInteger productId, BigInteger userId, BigInteger transportId,
                            String recordTime, String lng, String lat, String locationInfo,
                            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDRECORD,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(productId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(userId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(transportId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(recordTime),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(lng),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(lat),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(locationInfo)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple7<BigInteger, BigInteger, BigInteger, String, String, String, String> getAddRecordInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDRECORD,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple7<BigInteger, BigInteger, BigInteger, String, String, String, String>(

                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (BigInteger) results.get(2).getValue(),
                (String) results.get(3).getValue(),
                (String) results.get(4).getValue(),
                (String) results.get(5).getValue(),
                (String) results.get(6).getValue()
        );
    }

    public List<Record> getRecordsByProductId(BigInteger productId) throws ContractException {
        final Function function = new Function(FUNC_GETRECORDSBYPRODUCTID,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(productId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Record>>() {}));
        return executeCallWithSingleValueReturn(function, List.class);
    }

    public List<Record> getRecordsByTransportId(BigInteger transportId) throws ContractException {
        final Function function = new Function(FUNC_GETRECORDSBYTRANSPORTID,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(transportId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Record>>() {}));
        return executeCallWithSingleValueReturn(function, List.class);
    }

    public BigInteger lastBlockId() throws ContractException {
        final Function function = new Function(FUNC_LASTBLOCKID,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple8<BigInteger, BigInteger, BigInteger, BigInteger, String, String, String, String> records(
            BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_RECORDS,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple8<BigInteger, BigInteger, BigInteger, BigInteger, String, String, String, String>(
                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (BigInteger) results.get(2).getValue(),
                (BigInteger) results.get(3).getValue(),
                (String) results.get(4).getValue(),
                (String) results.get(5).getValue(),
                (String) results.get(6).getValue(),
                (String) results.get(7).getValue());
    }

    public static TransportRecordContract load(String contractAddress, Client client,
                                               CryptoKeyPair credential) {
        return new TransportRecordContract(contractAddress, client, credential);
    }

    public static TransportRecordContract deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(TransportRecordContract.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class Record extends DynamicStruct {
        public BigInteger id;

        public BigInteger productId;

        public BigInteger userId;

        public BigInteger transportId;

        public String recordTime;

        public String lng;

        public String lat;

        public String locationInfo;

        public Record(Uint256 id, Uint256 productId, Uint256 userId, Uint256 transportId,
                      Utf8String recordTime, Utf8String lng, Utf8String lat, Utf8String locationInfo) {
            super(id,productId,userId,transportId,recordTime,lng,lat,locationInfo);
            this.id = id.getValue();
            this.productId = productId.getValue();
            this.userId = userId.getValue();
            this.transportId = transportId.getValue();
            this.recordTime = recordTime.getValue();
            this.lng = lng.getValue();
            this.lat = lat.getValue();
            this.locationInfo = locationInfo.getValue();
        }

        public Record(BigInteger id, BigInteger productId, BigInteger userId,
                      BigInteger transportId, String recordTime, String lng, String lat,
                      String locationInfo) {
            super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(id),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(productId),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(userId),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(transportId),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(recordTime),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(lng),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(lat),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(locationInfo));
            this.id = id;
            this.productId = productId;
            this.userId = userId;
            this.transportId = transportId;
            this.recordTime = recordTime;
            this.lng = lng;
            this.lat = lat;
            this.locationInfo = locationInfo;
        }

        // 对于id属性的get方法
        public BigInteger getId() {
            return id;
        }
        // 对于productId属性的get方法
        public BigInteger getProductId() {
            return productId;
        }
        // 对于userId属性的get方法
        public BigInteger getUserId() {
            return userId;
        }
        // 对于transportId属性的get方法
        public BigInteger getTransportId() {
            return transportId;
        }
        // 对于recordTime属性的get方法
        public String getRecordTime() {
            return recordTime;
        }
        // 对于lng属性的get方法
        public String getLng() {
            return lng;
        }
        // 对于lat属性的get方法
        public String getLat() {
            return lat;
        }
        // 对于locationInfo属性的get方法
        public String getLocationInfo() {
            return locationInfo;
        }

    }

    public static class RecordAddedEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger id;

        public BigInteger productId;

        public BigInteger userId;
    }
}