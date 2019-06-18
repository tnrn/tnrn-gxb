import { NativeModules } from 'react-native';

const { GxbModule } = NativeModules;

export default {
    /**
     * 公信宝芝麻认证
     * @param  {[Object]} options [传入的参数] { token: '<通过后台接口得到的token>'}
     * @return {[Promise]}         [返回认证结果 1： 成功， 0： 失败： -1： 用户取消]
     */
    auth: function (options) {
        return GxbModule.auth(options);
    }
}