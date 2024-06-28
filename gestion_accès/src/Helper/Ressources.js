import { signIn } from "../Redux/Actions/Login/Login";

const module = {
    CoreUrl: "http://192.168.1.15:9011",
    CoreUrlB: "http://localhost:9011",
    Securite: {
        api: "gestion_acces/api",
        GroupUser: "groupusers",
        Groupes:"groupes",
        Groupesall:"groupes/filtre",
        AddGroupes: "groupes",
        Utilisateur:"utilisateurs",
        AddUtilisateur:"utilisateurs",
        accessmoduleuser:"accessmodulegrps",
        accessmoduleusers:"accessmoduleusers",
        accessmenuusers:"accessmenuusers",
        accessbuttonusers:"accessbuttonusers",
        signin:"signin",
        Modules: "modules",
        menus: "menus",
        buttons:"buttons",
        Delegation:"delegationaccess",
        accessmodulegrps:"accessmodulegrps",

    }
};

export default module;