package com.morpheusdata.addon

import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin
import com.morpheusdata.core.providers.ComputeTypePackageProvider
import com.morpheusdata.model.ComputeServerGroup
import com.morpheusdata.model.ComputeServerGroupPackage
import com.morpheusdata.model.ComputeTypePackage
import com.morpheusdata.model.Icon
import com.morpheusdata.model.OptionType
import com.morpheusdata.response.ServiceResponse

class TestAddonPackageTypeProvider implements ComputeTypePackageProvider {

    public static final String PACKAGE_PROVIDER_CODE = 'morpheus-addon-package-test-type'

    protected MorpheusContext morpheusContext
    protected Plugin plugin

    TestAddonPackageTypeProvider(Plugin plugin, MorpheusContext morpheusContext) {
        this.morpheusContext = morpheusContext
        this.plugin = plugin
    }

    @Override
    boolean isPlugin() {
        return true
    }

    @Override
    MorpheusContext getMorpheus() {
        return morpheusContext
    }

    @Override
    Plugin getPlugin() {
        return plugin
    }

    String getName() {
        "Morpheus Test"
    }

    String getCode() {
        return PACKAGE_PROVIDER_CODE
    }

    @Override
    String getDescription() {
        return "A quick test package that does nothing, just provides option types"
    }

    @Override
    String getType() {
        return "test"
    }

    @Override
    String getPackageType() {
        return "test"
    }

    @Override
    String getProviderType() {
        return ComputeTypePackage.ProviderType.MVM
    }

    @Override
    String getPackageVersion() {
        return "0.0.4"
    }

    Icon getCircularIcon() {
        return new Icon(path:"morpheus_circular.svg", darkPath: "morpheus_circular.svg")
    }

    List<OptionType> getOptionTypes() {
        return [
            new OptionType(
                name: 'Path',
                code: 'morpheus-addon-package-test-type-path',
                fieldName: 'path',
                displayOrder: 0,
                fieldLabel: 'Path',
                required: true,
                inputType: OptionType.InputType.TEXT,
                fieldContext: 'config'
            )
        ]
    }


    ServiceResponse<ComputeServerGroupPackage> installPackage(ComputeServerGroup serverGroup, ComputeServerGroupPackage computeServerGroupPackage) {
        println "\u001B[33mAC Log - TestAddonPackageTypeProvider:installPackage- called on ${serverGroup.name} to install ${computeServerGroupPackage.packageType.name}:${computeServerGroupPackage.packageType.packageVersion} config: ${computeServerGroupPackage.config}\u001B[0m"
        return ServiceResponse<ComputeServerGroupPackage>.success(computeServerGroupPackage)
    }

    ServiceResponse deletePackage(ComputeServerGroup serverGroup,ComputeServerGroupPackage computeServerGroupPackage){
        println "\u001B[33mAC Log - TestAddonPackageTypeProvider:deletePackage- called on ${serverGroup.name} to delete ${computeServerGroupPackage.packageType.name}:${computeServerGroupPackage.packageType.packageVersion}\u001B[0m"
        return ServiceResponse.success("implemented")
    }

    @Override
    ServiceResponse<ComputeServerGroupPackage> upgradePackage(ComputeServerGroup serverGroup, ComputeServerGroupPackage computeServerGroupPackage, String newVersion) {
        println "\u001B[33mAC Log - TestAddonPackageTypeProvider:upgradePackage- called on ${serverGroup.name} to upgrade ${computeServerGroupPackage.packageType.name}:${computeServerGroupPackage.packageType.packageVersion} to ${newVersion}\u001B[0m"
        computeServerGroupPackage.packageVersion = newVersion
        return ServiceResponse<ComputeServerGroupPackage>.success(computeServerGroupPackage)

    }
}
