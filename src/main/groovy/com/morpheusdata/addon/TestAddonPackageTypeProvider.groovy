package com.morpheusdata.addon

import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin
import com.morpheusdata.core.providers.AddonPackageTypeProvider
import com.morpheusdata.model.ComputeServerGroup
import com.morpheusdata.model.Icon
import com.morpheusdata.model.OptionType
import com.morpheusdata.model.AddonPackage
import com.morpheusdata.response.ServiceResponse

class TestAddonPackageTypeProvider implements AddonPackageTypeProvider {

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
        return "morpheus-addon-package-test-type"
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

    Collection<AddonPackage> getPackages() {
        return [
            new AddonPackage(
                version: "0.0.1",
                name: "Simple Test",
                code: "simple-test"
            )
        ]
    }

    ServiceResponse<AddonPackage> createPackage(ComputeServerGroup serverGroup, AddonPackage addonPackage) {
        return ServiceResponse<AddonPackage>.success(addonPackage)
    }

    ServiceResponse removePackage(ComputeServerGroup serverGroup, AddonPackage addonPackage){
        return ServiceResponse.error("Not implemented")
    }

}
