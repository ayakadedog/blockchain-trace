package com.restaurant.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicArray;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple8;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple9;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class TaskRecordContract extends Contract {

    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50611031806100206000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c8063344610671461005c578063a71a4ef41461008d578063af0cc8f0146100a2578063c2a4250b146100c2578063ecfe570a146100d9575b600080fd5b61006f61006a366004610c30565b6100ec565b60405161008499989796959493929190610c96565b60405180910390f35b6100a061009b366004610db8565b61035a565b005b6100b56100b0366004610c30565b610492565b6040516100849190610e8c565b6100cb60015481565b604051908152602001610084565b6100b56100e7366004610c30565b6107f3565b600060205280600052604060002060009150905080600001549080600101549080600201549080600301549080600401549080600501805461012d90610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461015990610f81565b80156101a65780601f1061017b576101008083540402835291602001916101a6565b820191906000526020600020905b81548152906001019060200180831161018957829003601f168201915b5050505050908060060180546101bb90610f81565b80601f01602080910402602001604051908101604052809291908181526020018280546101e790610f81565b80156102345780601f1061020957610100808354040283529160200191610234565b820191906000526020600020905b81548152906001019060200180831161021757829003601f168201915b50505050509080600701805461024990610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461027590610f81565b80156102c25780601f10610297576101008083540402835291602001916102c2565b820191906000526020600020905b8154815290600101906020018083116102a557829003601f168201915b5050505050908060080180546102d790610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461030390610f81565b80156103505780601f1061032557610100808354040283529160200191610350565b820191906000526020600020905b81548152906001019060200180831161033357829003601f168201915b5050505050905089565b6001805490600061036a83610fbc565b909155505060408051610120810182526001805480835260208084018d81528486018d8152606086018d8152608087018d815260a088018d815260c089018d905260e089018c905261010089018b905260009687528686529890952087518155925195830195909555516002820155925160038401559051600483015592518051929391926103ff9260058501920190610b4b565b5060c0820151805161041b916006840191602090910190610b4b565b5060e08201518051610437916007840191602090910190610b4b565b506101008201518051610454916008840191602090910190610b4b565b50506001546040518992508a91907facae88f8c5ced2bba1920b1be99fa426c3fff4cd1d557ce6665f281b096c3e2290600090a45050505050505050565b6060600060015467ffffffffffffffff8111156104b1576104b1610d15565b6040519080825280602002602001820160405280156104ea57816020015b6104d7610bcf565b8152602001906001900390816104cf5790505b509050600060015b60015481116107ea576000818152602081905260409020600101548514156107d85760008082815260200190815260200160002060405180610120016040529081600082015481526020016001820154815260200160028201548152602001600382015481526020016004820154815260200160058201805461057490610f81565b80601f01602080910402602001604051908101604052809291908181526020018280546105a090610f81565b80156105ed5780601f106105c2576101008083540402835291602001916105ed565b820191906000526020600020905b8154815290600101906020018083116105d057829003601f168201915b5050505050815260200160068201805461060690610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461063290610f81565b801561067f5780601f106106545761010080835404028352916020019161067f565b820191906000526020600020905b81548152906001019060200180831161066257829003601f168201915b5050505050815260200160078201805461069890610f81565b80601f01602080910402602001604051908101604052809291908181526020018280546106c490610f81565b80156107115780601f106106e657610100808354040283529160200191610711565b820191906000526020600020905b8154815290600101906020018083116106f457829003601f168201915b5050505050815260200160088201805461072a90610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461075690610f81565b80156107a35780601f10610778576101008083540402835291602001916107a3565b820191906000526020600020905b81548152906001019060200180831161078657829003601f168201915b5050505050815250508383815181106107be576107be610fe5565b602002602001018190525081806107d490610fbc565b9250505b806107e281610fbc565b9150506104f2565b50815292915050565b6060600060015467ffffffffffffffff81111561081257610812610d15565b60405190808252806020026020018201604052801561084b57816020015b610838610bcf565b8152602001906001900390816108305790505b509050600060015b60015481116107ea57600081815260208190526040902060020154851415610b39576000808281526020019081526020016000206040518061012001604052908160008201548152602001600182015481526020016002820154815260200160038201548152602001600482015481526020016005820180546108d590610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461090190610f81565b801561094e5780601f106109235761010080835404028352916020019161094e565b820191906000526020600020905b81548152906001019060200180831161093157829003601f168201915b5050505050815260200160068201805461096790610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461099390610f81565b80156109e05780601f106109b5576101008083540402835291602001916109e0565b820191906000526020600020905b8154815290600101906020018083116109c357829003601f168201915b505050505081526020016007820180546109f990610f81565b80601f0160208091040260200160405190810160405280929190818152602001828054610a2590610f81565b8015610a725780601f10610a4757610100808354040283529160200191610a72565b820191906000526020600020905b815481529060010190602001808311610a5557829003601f168201915b50505050508152602001600882018054610a8b90610f81565b80601f0160208091040260200160405190810160405280929190818152602001828054610ab790610f81565b8015610b045780601f10610ad957610100808354040283529160200191610b04565b820191906000526020600020905b815481529060010190602001808311610ae757829003601f168201915b505050505081525050838381518110610b1f57610b1f610fe5565b60200260200101819052508180610b3590610fbc565b9250505b80610b4381610fbc565b915050610853565b828054610b5790610f81565b90600052602060002090601f016020900481019282610b795760008555610bbf565b82601f10610b9257805160ff1916838001178555610bbf565b82800160010185558215610bbf579182015b82811115610bbf578251825591602001919060010190610ba4565b50610bcb929150610c1b565b5090565b6040518061012001604052806000815260200160008152602001600081526020016000815260200160008152602001606081526020016060815260200160608152602001606081525090565b5b80821115610bcb5760008155600101610c1c565b600060208284031215610c4257600080fd5b5035919050565b6000815180845260005b81811015610c6f57602081850181015186830182015201610c53565b81811115610c81576000602083870101525b50601f01601f19169290920160200192915050565b60006101208b83528a60208401528960408401528860608401528760808401528060a0840152610cc881840188610c49565b905082810360c0840152610cdc8187610c49565b905082810360e0840152610cf08186610c49565b9050828103610100840152610d058185610c49565b9c9b505050505050505050505050565b634e487b7160e01b600052604160045260246000fd5b600082601f830112610d3c57600080fd5b813567ffffffffffffffff80821115610d5757610d57610d15565b604051601f8301601f19908116603f01168101908282118183101715610d7f57610d7f610d15565b81604052838152866020858801011115610d9857600080fd5b836020870160208301376000602085830101528094505050505092915050565b600080600080600080600080610100898b031215610dd557600080fd5b88359750602089013596506040890135955060608901359450608089013567ffffffffffffffff80821115610e0957600080fd5b610e158c838d01610d2b565b955060a08b0135915080821115610e2b57600080fd5b610e378c838d01610d2b565b945060c08b0135915080821115610e4d57600080fd5b610e598c838d01610d2b565b935060e08b0135915080821115610e6f57600080fd5b50610e7c8b828c01610d2b565b9150509295985092959890939650565b60006020808301818452808551808352604092508286019150828160051b87010184880160005b83811015610f7357603f19898403018552815161012081518552888201518986015287820151888601526060808301518187015250608080830151818701525060a0808301518282880152610f0a83880182610c49565b9250505060c08083015186830382880152610f258382610c49565b9250505060e08083015186830382880152610f408382610c49565b925050506101008083015192508582038187015250610f5f8183610c49565b968901969450505090860190600101610eb3565b509098975050505050505050565b600181811c90821680610f9557607f821691505b60208210811415610fb657634e487b7160e01b600052602260045260246000fd5b50919050565b6000600019821415610fde57634e487b7160e01b600052601160045260246000fd5b50","60010190565b634e487b7160e01b600052603260045260246000fdfea264697066735822122074b76cb7e83ad10621781a5d5e2e55b29caf758e8e5ad976e18af480c3a67cfb64736f6c634300080b0033"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50611031806100206000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c806330b9e81a1461005c5780635aa239db14610085578063681ebc9f1461009a578063a535c562146100b1578063c860f85e146100c4575b600080fd5b61006f61006a366004610c30565b6100ec565b60405161007c9190610c96565b60405180910390f35b610098610093366004610e2e565b61044d565b005b6100a360015481565b60405190815260200161007c565b61006f6100bf366004610c30565b610585565b6100d76100d2366004610c30565b6108dd565b60405161007c99989796959493929190610f02565b6060600060015467ffffffffffffffff81111561010b5761010b610d8b565b60405190808252806020026020018201604052801561014457816020015b610131610b4b565b8152602001906001900390816101295790505b509050600060015b600154811161044457600081815260208190526040902060010154851415610432576000808281526020019081526020016000206040518061012001604052908160008201548152602001600182015481526020016002820154815260200160038201548152602001600482015481526020016005820180546101ce90610f81565b80601f01602080910402602001604051908101604052809291908181526020018280546101fa90610f81565b80156102475780601f1061021c57610100808354040283529160200191610247565b820191906000526020600020905b81548152906001019060200180831161022a57829003601f168201915b5050505050815260200160068201805461026090610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461028c90610f81565b80156102d95780601f106102ae576101008083540402835291602001916102d9565b820191906000526020600020905b8154815290600101906020018083116102bc57829003601f168201915b505050505081526020016007820180546102f290610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461031e90610f81565b801561036b5780601f106103405761010080835404028352916020019161036b565b820191906000526020600020905b81548152906001019060200180831161034e57829003601f168201915b5050505050815260200160088201805461038490610f81565b80601f01602080910402602001604051908101604052809291908181526020018280546103b090610f81565b80156103fd5780601f106103d2576101008083540402835291602001916103fd565b820191906000526020600020905b8154815290600101906020018083116103e057829003601f168201915b50505050508152505083838151811061041857610418610fbc565b6020026020010181905250818061042e90610fd2565b9250505b8061043c81610fd2565b91505061014c565b50815292915050565b6001805490600061045d83610fd2565b909155505060408051610120810182526001805480835260208084018d81528486018d8152606086018d8152608087018d815260a088018d815260c089018d905260e089018c905261010089018b905260009687528686529890952087518155925195830195909555516002820155925160038401559051600483015592518051929391926104f29260058501920190610b97565b5060c0820151805161050e916006840191602090910190610b97565b5060e0820151805161052a916007840191602090910190610b97565b506101008201518051610547916008840191602090910190610b97565b50506001546040518992508a91907f8ab2a636d95896cdfa3b100b15c58fa15a1f34a692c5a5875ca3a9d836e0e9e590600090a45050505050505050565b6060600060015467ffffffffffffffff8111156105a4576105a4610d8b565b6040519080825280602002602001820160405280156105dd57816020015b6105ca610b4b565b8152602001906001900390816105c25790505b509050600060015b6001548111610444576000818152602081905260409020600201548514156108cb5760008082815260200190815260200160002060405180610120016040529081600082015481526020016001820154815260200160028201548152602001600382015481526020016004820154815260200160058201805461066790610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461069390610f81565b80156106e05780601f106106b5576101008083540402835291602001916106e0565b820191906000526020600020905b8154815290600101906020018083116106c357829003601f168201915b505050505081526020016006820180546106f990610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461072590610f81565b80156107725780601f1061074757610100808354040283529160200191610772565b820191906000526020600020905b81548152906001019060200180831161075557829003601f168201915b5050505050815260200160078201805461078b90610f81565b80601f01602080910402602001604051908101604052809291908181526020018280546107b790610f81565b80156108045780601f106107d957610100808354040283529160200191610804565b820191906000526020600020905b8154815290600101906020018083116107e757829003601f168201915b5050505050815260200160088201805461081d90610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461084990610f81565b80156108965780601f1061086b57610100808354040283529160200191610896565b820191906000526020600020905b81548152906001019060200180831161087957829003601f168201915b5050505050815250508383815181106108b1576108b1610fbc565b602002602001018190525081806108c790610fd2565b9250505b806108d581610fd2565b9150506105e5565b600060205280600052604060002060009150905080600001549080600101549080600201549080600301549080600401549080600501805461091e90610f81565b80601f016020809104026020016040519081016040528092919081815260200182805461094a90610f81565b80156109975780601f1061096c57610100808354040283529160200191610997565b820191906000526020600020905b81548152906001019060200180831161097a57829003601f168201915b5050505050908060060180546109ac90610f81565b80601f01602080910402602001604051908101604052809291908181526020018280546109d890610f81565b8015610a255780601f106109fa57610100808354040283529160200191610a25565b820191906000526020600020905b815481529060010190602001808311610a0857829003601f168201915b505050505090806007018054610a3a90610f81565b80601f0160208091040260200160405190810160405280929190818152602001828054610a6690610f81565b8015610ab35780601f10610a8857610100808354040283529160200191610ab3565b820191906000526020600020905b815481529060010190602001808311610a9657829003601f168201915b505050505090806008018054610ac890610f81565b80601f0160208091040260200160405190810160405280929190818152602001828054610af490610f81565b8015610b415780601f10610b1657610100808354040283529160200191610b41565b820191906000526020600020905b815481529060010190602001808311610b2457829003601f168201915b5050505050905089565b6040518061012001604052806000815260200160008152602001600081526020016000815260200160008152602001606081526020016060815260200160608152602001606081525090565b828054610ba390610f81565b90600052602060002090601f016020900481019282610bc55760008555610c0b565b82601f10610bde57805160ff1916838001178555610c0b565b82800160010185558215610c0b579182015b82811115610c0b578251825591602001919060010190610bf0565b50610c17929150610c1b565b5090565b5b80821115610c175760008155600101610c1c565b600060208284031215610c4257600080fd5b5035919050565b6000815180845260005b81811015610c6f57602081850181015186830182015201610c53565b81811115610c81576000602083870101525b50601f01601f19169290920160200192915050565b60006020808301818452808551808352604092508286019150828160051b87010184880160005b83811015610d7d57603f19898403018552815161012081518552888201518986015287820151888601526060808301518187015250608080830151818701525060a0808301518282880152610d1483880182610c49565b9250505060c08083015186830382880152610d2f8382610c49565b9250505060e08083015186830382880152610d4a8382610c49565b925050506101008083015192508582038187015250610d698183610c49565b968901969450505090860190600101610cbd565b509098975050505050505050565b63b95aa35560e01b600052604160045260246000fd5b600082601f830112610db257600080fd5b813567ffffffffffffffff80821115610dcd57610dcd610d8b565b604051601f8301601f19908116603f01168101908282118183101715610df557610df5610d8b565b81604052838152866020858801011115610e0e57600080fd5b836020870160208301376000602085830101528094505050505092915050565b600080600080600080600080610100898b031215610e4b57600080fd5b88359750602089013596506040890135955060608901359450608089013567ffffffffffffffff80821115610e7f57600080fd5b610e8b8c838d01610da1565b955060a08b0135915080821115610ea157600080fd5b610ead8c838d01610da1565b945060c08b0135915080821115610ec357600080fd5b610ecf8c838d01610da1565b935060e08b0135915080821115610ee557600080fd5b50610ef28b828c01610da1565b9150509295985092959890939650565b60006101208b83528a60208401528960408401528860608401528760808401528060a0840152610f3481840188610c49565b905082810360c0840152610f488187610c49565b905082810360e0840152610f5c8186610c49565b9050828103610100840152610f718185610c49565b9c9b505050505050505050505050565b600181811c90821680610f9557607f821691505b60208210811415610fb65763b95aa35560e01b600052602260045260246000fd5b50919050565b63b95aa35560e01b600052603260045260246000fd5b6000600019821415610ff45763","b95aa35560e01b600052601160045260246000fd5b506001019056fea26469706673582212207a33ee20f0d38c8b09f79cddfbcfe5ff1779aaf2d171bc76dad1602957fdc05364736f6c634300080b0033"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"taskId\",\"type\":\"uint256\"},{\"indexed\":true,\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"}],\"name\":\"RecordAdded\",\"type\":\"event\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":4,\"value\":[1]}],\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"taskId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"userId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"companyId\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"recordTime\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"remarks\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"taskName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"content\",\"type\":\"string\"}],\"name\":\"addRecord\",\"outputs\":[],\"selector\":[2803519220,1520581083],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":4,\"value\":[1]}],\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"}],\"name\":\"getRecordsByProductId\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"taskId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"userId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"companyId\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"recordTime\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"remarks\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"taskName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"content\",\"type\":\"string\"}],\"internalType\":\"struct TaskRecordContract.Record[]\",\"name\":\"\",\"type\":\"tuple[]\"}],\"selector\":[3976091402,2771764578],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":4,\"value\":[1]}],\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"taskId\",\"type\":\"uint256\"}],\"name\":\"getRecordsByTaskId\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"taskId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"userId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"companyId\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"recordTime\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"remarks\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"taskName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"content\",\"type\":\"string\"}],\"internalType\":\"struct TaskRecordContract.Record[]\",\"name\":\"\",\"type\":\"tuple[]\"}],\"selector\":[2936850672,817489946],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":4,\"value\":[1]}],\"inputs\":[],\"name\":\"lastBlockId\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"selector\":[3265537291,1746844831],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":3,\"slot\":0,\"value\":[0]}],\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"name\":\"records\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"id\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"taskId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"productId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"userId\",\"type\":\"uint256\"},{\"internalType\":\"uint256\",\"name\":\"companyId\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"recordTime\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"remarks\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"taskName\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"content\",\"type\":\"string\"}],\"selector\":[877006951,3361798238],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ADDRECORD = "addRecord";

    public static final String FUNC_GETRECORDSBYPRODUCTID = "getRecordsByProductId";

    public static final String FUNC_GETRECORDSBYTASKID = "getRecordsByTaskId";

    public static final String FUNC_LASTBLOCKID = "lastBlockId";

    public static final String FUNC_RECORDS = "records";

    public static final Event RECORDADDED_EVENT = new Event("RecordAdded",
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}));
    ;
    public String getContractAddress() {
        return contractAddress;
    }

    // Get function for client
    public Client getClient() {
        return client;
    }

    // Get function for credential
    public CryptoKeyPair getCredential() {
        return credential;
    }
    protected TaskRecordContract(String contractAddress, Client client, CryptoKeyPair credential) {
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
            typedResponse.taskId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.productId = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public TransactionReceipt addRecord(BigInteger taskId, BigInteger productId, BigInteger userId,
                                        BigInteger companyId, String recordTime, String remarks, String taskName,
                                        String content) {
        final Function function = new Function(
                FUNC_ADDRECORD,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(taskId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(productId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(userId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(companyId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(recordTime),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(remarks),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(taskName),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(content)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForAddRecord(BigInteger taskId, BigInteger productId,
                                                   BigInteger userId, BigInteger companyId, String recordTime, String remarks,
                                                   String taskName, String content) {
        final Function function = new Function(
                FUNC_ADDRECORD,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(taskId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(productId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(userId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(companyId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(recordTime),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(remarks),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(taskName),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(content)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String addRecord(BigInteger taskId, BigInteger productId, BigInteger userId,
                            BigInteger companyId, String recordTime, String remarks, String taskName,
                            String content, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_ADDRECORD,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(taskId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(productId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(userId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(companyId),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(recordTime),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(remarks),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(taskName),
                        new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(content)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple8<BigInteger, BigInteger, BigInteger, BigInteger, String, String, String, String> getAddRecordInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDRECORD,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple8<BigInteger, BigInteger, BigInteger, BigInteger, String, String, String, String>(

                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (BigInteger) results.get(2).getValue(),
                (BigInteger) results.get(3).getValue(),
                (String) results.get(4).getValue(),
                (String) results.get(5).getValue(),
                (String) results.get(6).getValue(),
                (String) results.get(7).getValue()
        );
    }

    public List<Record> getRecordsByProductId(BigInteger productId) throws ContractException {
        final Function function = new Function(FUNC_GETRECORDSBYPRODUCTID,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(productId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Record>>() {}));
        return executeCallWithSingleValueReturn(function, List.class);
    }

    public List<Record> getRecordsByTaskId(BigInteger taskId) throws ContractException {
        final Function function = new Function(FUNC_GETRECORDSBYTASKID,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(taskId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Record>>() {}));
        return executeCallWithSingleValueReturn(function, List.class);
    }

    public BigInteger lastBlockId() throws ContractException {
        final Function function = new Function(FUNC_LASTBLOCKID,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Tuple9<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String, String, String, String> records(
            BigInteger param0) throws ContractException {
        final Function function = new Function(FUNC_RECORDS,
                Arrays.<Type>asList(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple9<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, String, String, String, String>(
                (BigInteger) results.get(0).getValue(),
                (BigInteger) results.get(1).getValue(),
                (BigInteger) results.get(2).getValue(),
                (BigInteger) results.get(3).getValue(),
                (BigInteger) results.get(4).getValue(),
                (String) results.get(5).getValue(),
                (String) results.get(6).getValue(),
                (String) results.get(7).getValue(),
                (String) results.get(8).getValue());
    }

    public static TaskRecordContract load(String contractAddress, Client client,
                                          CryptoKeyPair credential) {
        return new TaskRecordContract(contractAddress, client, credential);
    }

    public static TaskRecordContract deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(TaskRecordContract.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class Record extends DynamicStruct {
        public BigInteger id;

        public BigInteger taskId;

        public BigInteger productId;

        public BigInteger userId;

        public BigInteger companyId;

        public String recordTime;

        public String remarks;

        public String taskName;

        public String content;

        public Record(Uint256 id, Uint256 taskId, Uint256 productId, Uint256 userId,
                      Uint256 companyId, Utf8String recordTime, Utf8String remarks, Utf8String taskName,
                      Utf8String content) {
            super(id,taskId,productId,userId,companyId,recordTime,remarks,taskName,content);
            this.id = id.getValue();
            this.taskId = taskId.getValue();
            this.productId = productId.getValue();
            this.userId = userId.getValue();
            this.companyId = companyId.getValue();
            this.recordTime = recordTime.getValue();
            this.remarks = remarks.getValue();
            this.taskName = taskName.getValue();
            this.content = content.getValue();
        }

        public Record(BigInteger id, BigInteger taskId, BigInteger productId, BigInteger userId,
                      BigInteger companyId, String recordTime, String remarks, String taskName,
                      String content) {
            super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(id),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(taskId),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(productId),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(userId),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(companyId),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(recordTime),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(remarks),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(taskName),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(content));
            this.id = id;
            this.taskId = taskId;
            this.productId = productId;
            this.userId = userId;
            this.companyId = companyId;
            this.recordTime = recordTime;
            this.remarks = remarks;
            this.taskName = taskName;
            this.content = content;
        }
        public BigInteger getId() {
            return id;
        }

        public BigInteger getTaskId() {
            return taskId;
        }

        public BigInteger getProductId() {
            return productId;
        }

        public BigInteger getUserId() {
            return userId;
        }

        public BigInteger getCompanyId() {
            return companyId;
        }

        public String getRecordTime() {
            return recordTime;
        }

        public String getRemarks() {
            return remarks;
        }

        public String getTaskName() {
            return taskName;
        }

        public String getContent() {
            return content;
        }
    }

    public static class RecordAddedEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger id;

        public BigInteger taskId;

        public BigInteger productId;
    }
}