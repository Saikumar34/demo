$connectionName = "AzureRunAsConnection"
try
{
    # Get the connection "AzureRunAsConnection "
    $servicePrincipalConnection=Get-AutomationConnection -Name $connectionName
    Add-azAccount `
        -ServicePrincipal `
        -TenantId $servicePrincipalConnection.TenantId `
        -ApplicationId $servicePrincipalConnection.ApplicationId `
        -CertificateThumbprint $servicePrincipalConnection.CertificateThumbprint | out-null
}
catch {
    if (!$servicePrincipalConnection)
    {
        $ErrorMessage = "Connection $connectionName not found."
        throw $ErrorMessage
    } else{
        Write-Error -Message $_.Exception
        throw $_.Exception
    }
}

$subscriptionId = "70b49f2f-13aa-4f4c-9bf3-17f60d0c546a"
$resourceGroup = "ITXLAB"
$vmL = "centos-vic3"
$vmW = "ad2"
 
#Select Azure subscription
Set-AzContext -Subscription $subscriptionId

$out = Invoke-AzVmRunCommand -ResourceGroupName $resourceGroup -Name $vmW -CommandId 'RunPowerShellScript' -Scriptstring "wmic bios get serialnumber"
$out.Value[0].Message
