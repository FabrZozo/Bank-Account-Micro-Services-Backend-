export interface CustomerDto{
  firstname : string;
  lastname :string;
  email: string;

}
export interface Customer extends CustomerDto{
  id: number;
}
