export interface INotificator{
    notifySuccess( message: string) : void;
    notifyInfo( message: string) : void;
    notifyError( message: string) : void;
}